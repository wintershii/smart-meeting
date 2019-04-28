package com.winter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winter.common.ServerResponse;
import com.winter.dao.*;
import com.winter.domain.Meeting;
import com.winter.service.IMeetingService;
import com.winter.util.DateTimeUtil;
import com.winter.vo.MeetingVo;
import com.winter.vo.UserAccessInfo;
import com.winter.vo.UserStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MeetingServiceImpl implements IMeetingService {

    private MeetingMapper meetingMapper;

    private UserMeetingMapper userMeetingMapper;

    private UserMapper userMapper;

    private RoomMapper roomMapper;

    private MeetingFileMapper meetingFileMapper;

    private MeetingVoteMapper meetingVoteMapper;

    @Autowired
    public void setMeetingMapper(MeetingMapper meetingMapper) {
        this.meetingMapper = meetingMapper;
    }

    @Autowired
    public void setUserMeetingMapper(UserMeetingMapper userMeetingMapper) {
        this.userMeetingMapper = userMeetingMapper;
    }

    @Autowired
    public void setRoomMapper(RoomMapper roomMapper) {
        this.roomMapper = roomMapper;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setMeetingFileMapper(MeetingFileMapper meetingFileMapper) {
        this.meetingFileMapper = meetingFileMapper;
    }

    @Autowired
    public void setMeetingVoteMapper(MeetingVoteMapper meetingVoteMapper) {
        this.meetingVoteMapper = meetingVoteMapper;
    }

    /**
     * 获取用户所有的会议简介
     * @param userId
     * @param type
     * @return
     */
    @Override
    public List<MeetingVo> getUserMeetings(Integer userId, Integer type) {
        List<Meeting> list;
        if (type == 1) {
            //获取正在参加和还未参加的会议list
            list =  meetingMapper.getMeetingsOngoing(userId);
        } else {
            //获取参加过的会议list
            list = meetingMapper.getMeetingsFinished(userId);
        }
        return meetingToVo(list,userId);
    }


    /**
     * 根据会议id获取会议信息
     * @param meetingId
     * @return
     */
    @Override
    public MeetingVo getMeetingById(Integer meetingId) {
        Meeting meeting = meetingMapper.selectByPrimaryKey(meetingId);
        if (meeting != null) {
            return meetingToVo(meeting);
        }
        return null;
    }

    /**
     * 根据会议id获取用户状态
     * @param meetingId
     * @return
     */
    @Override
    public List<UserStatus> getUserStatus(Integer meetingId) {
        List<UserStatus> userStatus = userMeetingMapper.getUserStatus(meetingId);
        if (userStatus != null && userStatus.size() != 0) {
            return userStatus;
        }
        return null;
    }

    /**
     * 获取页面的会议信息(每页五条)
     * @param roomId
     * @param page
     * @return
     */
    @Override
    public PageInfo getPageMeetings(Integer roomId, Integer page) {
        PageHelper.startPage(page,5);
        List<Meeting> meetingList = meetingMapper.getMeetingByRoomId(roomId,null);
        PageInfo pageResult = new PageInfo(meetingList);
        return pageResult;
    }

    @Override
    public int whetherBook(Integer roomId, Date startTime, Date endTime) {
        return meetingMapper.whetherBook(roomId,startTime,endTime);
    }


    @Override
    @Transactional
    public int bookMeeting(Meeting meeting) {
        if (this.whetherBook(meeting.getRoomId(),meeting.getStartTime(),meeting.getEndTime()) == 0) {
            return meetingMapper.bookMeeting(meeting);
        }
        return 0;
    }


    @Override
    public Integer getMeetingMasterId(Integer meetingId) {
        return meetingMapper.getMeetingMasterId(meetingId);
    }



    @Override
    public int setMeetingStatus(Integer meetingId, Integer status) {

        return meetingMapper.setMeetingStatus(meetingId,status);
    }


    @Override
    public ServerResponse<List<UserAccessInfo>> getAllUserByMeetingId(Integer meetingId) {
        List<UserAccessInfo> list = meetingMapper.getAllUserByMeetingId(meetingId);
        if (list != null && list.size() != 0) {
            return ServerResponse.createBySuccess(list);
        }
        return ServerResponse.createByErrorMessage("获取用户数据失败");
    }


    public boolean updateAllMeetingInfo() {
        int finished = meetingMapper.updateAllMeeting();
        int onGoing = meetingMapper.updateAllMeetingOngoing();
        if (finished > 0 && onGoing > 0) {
            return true;
        }
        return false;
    }


    @Override
    public Integer getRoomIdByMeetingId(Integer meetingId) {
        return meetingMapper.getRoomIdByMeetingId(meetingId);
    }

    @Override
    public ServerResponse editNote(Integer meetingId, Integer userId, String note) {
        int resultCount = meetingMapper.editNote(meetingId,userId,note);
        if (resultCount > 0) {
            return ServerResponse.createBySuccess("编辑信息成功!");
        }
        return ServerResponse.createByErrorMessage("编辑信息失败");
    }


    @Override
    public ServerResponse<String> getMeetingNote(Integer meetingId, Integer userId) {
        String note = meetingMapper.getMeetingNote(meetingId,userId);
        if (note == null || StringUtils.isBlank(note)) {
            return ServerResponse.createBySuccess("");
        }
        return ServerResponse.createBySuccess(note);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServerResponse deleteMeeting(Integer meetingId) {
        userMeetingMapper.deleteByMeetingId(meetingId);
        meetingFileMapper.deleteByMeetingId(meetingId);
        List<Integer> voteIds = meetingVoteMapper.selectVoteIdByMeetingId(meetingId);
        for (Integer voteId : voteIds) {
            meetingVoteMapper.deleteOptionsByVoteId(voteId);
            meetingVoteMapper.deleteUserOptionByVoteId(voteId);
            meetingVoteMapper.deleteByPrimaryKey(voteId);
        }


        int resultCount = meetingMapper.deleteByPrimaryKey(meetingId);

        if (resultCount > 0) {
            return ServerResponse.createBySuccess("删除会议信息成功!");
        }
        return ServerResponse.createByErrorMessage("删除会议信息失败!");
    }


    /**
     * 会议转vo
     * @param meetings
     * @return
     */
    public List<MeetingVo> meetingToVo(List<Meeting> meetings) {
        List<MeetingVo> meetingVoList = new ArrayList<>();
        for (Meeting meeting : meetings) {
            meetingVoList.add(meetingToVo(meeting));
        }
        return meetingVoList;
    }

    private List<MeetingVo> meetingToVo(List<Meeting> meetings,Integer userId) {
        List<MeetingVo> meetingVoList = new ArrayList<>();
        for (Meeting meeting : meetings) {
            meetingVoList.add(meetingToVo(meeting,userId));
        }
        return meetingVoList;
    }



    private MeetingVo meetingToVo(Meeting meeting) {
            MeetingVo meetingVo = new MeetingVo();
            meetingVo.setMeetingId(meeting.getId());
            meetingVo.setStartTime(DateTimeUtil.dateToStr(meeting.getStartTime()));
            meetingVo.setEndTime(DateTimeUtil.dateToStr(meeting.getEndTime()));
            meetingVo.setMeetingName(meeting.getMeetingName());
            meetingVo.setMasterId(meeting.getMasterId());
            meetingVo.setRoomId(meeting.getRoomId());
            meetingVo.setRoomName(roomMapper.getRoomNameById(meeting.getRoomId()));
            meetingVo.setMeetingIntro(meeting.getMeetingIntro());
            meetingVo.setPeopleNum(userMeetingMapper.getPeopleNum(meeting.getId()));
            meetingVo.setStatus(meeting.getStatus());
            meetingVo.setMemberStatus(userMeetingMapper.getUserStatus(meeting.getId()));
            meetingVo.setMasterName(userMapper.getNameById(meeting.getMasterId()));
            return meetingVo;
    }

    private MeetingVo meetingToVo(Meeting meeting,Integer userId) {
        MeetingVo meetingVo = meetingToVo(meeting);
        meetingVo.setUserStatus(userMeetingMapper.getOneStatusByUserAndMeeting(userId,meeting.getId()));
        return meetingVo;
    }

}
