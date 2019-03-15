package com.winter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winter.common.ServerResponse;
import com.winter.dao.MeetingMapper;
import com.winter.dao.RoomMapper;
import com.winter.dao.UserMapper;
import com.winter.dao.UserMeetingMapper;
import com.winter.domain.Meeting;
import com.winter.domain.UserMeeting;
import com.winter.service.IMeetingService;
import com.winter.util.DateTimeUtil;
import com.winter.vo.MeetingVo;
import com.winter.vo.UserStatus;
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
        if (userStatus != null) {
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

    /**
     * 会议转vo
     * @param meetings
     * @return
     */
    private List<MeetingVo> meetingToVo(List<Meeting> meetings) {
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
            return meetingVo;
    }

    private MeetingVo meetingToVo(Meeting meeting,Integer userId) {
        MeetingVo meetingVo = meetingToVo(meeting);
        meetingVo.setUserStatus(userMeetingMapper.getOneStatusByUserAndMeeting(userId,meeting.getId()));
        return meetingVo;
    }

}
