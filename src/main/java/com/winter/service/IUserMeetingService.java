package com.winter.service;

import com.winter.domain.UserMeeting;

public interface IUserMeetingService {
    int inviteMeetingMember(UserMeeting userMeeting);

    boolean checkExist(Integer userId, Integer meetingId);
}
