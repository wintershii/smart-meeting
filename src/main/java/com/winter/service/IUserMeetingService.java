package com.winter.service;

import com.winter.common.ServerResponse;
import com.winter.domain.UserMeeting;

public interface IUserMeetingService {
    ServerResponse inviteMeetingMember(UserMeeting userMeeting);

    boolean checkExist(Integer userId, Integer meetingId);

    ServerResponse uploadUserMeetingStatus(Integer userId, Integer meetingId, Integer userStatus);

    ServerResponse inviteMembers(Integer[] userIds, Integer meetingId);
}
