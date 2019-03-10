package com.winter.service.impl;

import com.winter.dao.UserMapper;
import com.winter.dao.UserMeetingMapper;
import com.winter.domain.UserMeeting;
import com.winter.service.IMeetingService;
import com.winter.util.DateTimeUtil;
import com.winter.vo.MeetingVo;
import com.winter.vo.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MeetingServiceImpl implements IMeetingService {

    private UserMeetingMapper meetingMapper;

    private UserMapper userMapper;

    @Autowired
    public void setMeetingMapper(UserMeetingMapper meetingMapper) {
        this.meetingMapper = meetingMapper;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 获取用户所有的会议简介
     * @param userId
     * @param type
     * @return
     */
    @Override
    public List<MeetingVo> getUserMeetings(Integer userId, Integer type) {
        List<UserMeeting> list;
        if (type == 1) {
            //获取正在参加和还未参加的会议list
            list =  meetingMapper.getUserMeetingsOngoing(userId);
        } else {
            //获取参加过的会议list
            list = meetingMapper.getUserMeetingsFinished(userId);
        }
        return meetingToVo(list);
    }


    @Override
    public MeetingVo getMeetingById(Integer meetingId) {
        UserMeeting meeting = meetingMapper.selectByPrimaryKey(meetingId);
        if (meeting != null) {
            return meetingToVo(meeting);
        }
        return null;
    }


    @Override
    public List<UserStatus> getUserStatus(Integer meetingId) {
        List<UserStatus> userStatus = meetingMapper.getUserStatus(meetingId);
        if (userStatus != null) {
            return userStatus;
        }
        return null;
    }

    /**
     * 会议转vo
     * @param meetings
     * @return
     */
    private List<MeetingVo> meetingToVo(List<UserMeeting> meetings) {
        List<MeetingVo> meetingVoList = new ArrayList<>();
        for (UserMeeting meeting : meetings) {
            meetingVoList.add(meetingToVo(meeting));
        }
        return meetingVoList;
    }



    private MeetingVo meetingToVo(UserMeeting meeting) {
            MeetingVo meetingVo = new MeetingVo();
            meetingVo.setMeetingId(meeting.getId());
            meetingVo.setStartTime(DateTimeUtil.dateToStr(meeting.getStartTime()));
            meetingVo.setEndTime(DateTimeUtil.dateToStr(meeting.getEndTime()));
            meetingVo.setMeetingName(meeting.getMeetingName());
            meetingVo.setMasterId(meeting.getMasterId());
            meetingVo.setRoomId(meeting.getRoomId());
            meetingVo.setRoomName("");
            meetingVo.setMeetingIntro(meeting.getMeetingIntro());
            meetingVo.setPeopleNum(meetingMapper.getPeopleNum(meeting.getId()));
            meetingVo.setStatus(meeting.getStatus());
            meetingVo.setUserStatus(meeting.getUserStatus());
            return meetingVo;
    }

}
