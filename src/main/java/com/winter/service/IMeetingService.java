package com.winter.service;

import com.winter.domain.UserMeeting;

import java.util.List;

public interface IMeetingService {
    List<UserMeeting> getUserMeetings(Integer userId, int type);
}
