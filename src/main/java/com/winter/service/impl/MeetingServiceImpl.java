package com.winter.service.impl;

import com.winter.dao.UserMeetingMapper;
import com.winter.domain.UserMeeting;
import com.winter.service.IMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingServiceImpl implements IMeetingService {

    private UserMeetingMapper meetingMapper;

    @Autowired
    public void setMeetingMapper(UserMeetingMapper meetingMapper) {
        this.meetingMapper = meetingMapper;
    }

    public List<UserMeeting> getUserMeetings(Integer userId, int type) {
        if (type == 1) {
            return meetingMapper.getUserMeetings(userId,null);
        }
        return meetingMapper.getUserMeetings(userId,1);
    }
}
