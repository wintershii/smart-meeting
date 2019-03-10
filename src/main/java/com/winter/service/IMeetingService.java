package com.winter.service;

import com.winter.vo.MeetingVo;
import com.winter.vo.UserStatus;

import java.util.List;
import java.util.Map;

public interface IMeetingService {
    List<MeetingVo> getUserMeetings(Integer userId, Integer type);
    MeetingVo getMeetingById(Integer meetingId);
    List<UserStatus> getUserStatus(Integer meeting);
}
