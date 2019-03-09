package com.winter.service;

import com.winter.vo.MeetingVo;

import java.util.List;
import java.util.Map;

public interface IMeetingService {
    List<MeetingVo> getUserMeetings(Integer userId, int type);
    MeetingVo getMeetingById(Integer meetingId);
    Map<Integer,Integer> getUserStatus(Integer meeting);
}
