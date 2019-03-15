package com.winter.service.impl;

import com.winter.dao.UserMeetingMapper;
import com.winter.domain.UserMeeting;
import com.winter.service.IUserMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMeetingServiceImpl implements IUserMeetingService {

    private UserMeetingMapper userMeetingMapper;

    @Autowired
    public void setUserMeetingMapper(UserMeetingMapper userMeetingMapper) {
        this.userMeetingMapper = userMeetingMapper;
    }

    @Override
    public int inviteMeetingMember(UserMeeting userMeeting) {
        int resultCount = userMeetingMapper.insert(userMeeting);
        return resultCount;
    }

    @Override
    public boolean checkExist(Integer userId, Integer meetingId) {
        int resultCount = userMeetingMapper.checkExist(userId,meetingId);
        if (resultCount > 0) {
            return false;
        }
        return true;
    }
}
