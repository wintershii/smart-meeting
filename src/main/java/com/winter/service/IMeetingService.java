package com.winter.service;

import com.github.pagehelper.PageInfo;
import com.winter.common.ServerResponse;
import com.winter.domain.Meeting;
import com.winter.vo.MeetingVo;
import com.winter.vo.UserAccessInfo;
import com.winter.vo.UserStatus;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IMeetingService {
    List<MeetingVo> getUserMeetings(Integer userId, Integer type);
    MeetingVo getMeetingById(Integer meetingId);
    List<UserStatus> getUserStatus(Integer meeting);
    PageInfo getPageMeetings(Integer roomId, Integer page);
    int whetherBook(Integer roomId, Date startTime, Date endTime);

    int bookMeeting(Meeting meeting);

    Integer getMeetingMasterId(Integer meetingId);

    int setMeetingStatus(Integer meetingId, Integer status);

    ServerResponse<List<UserAccessInfo>> getAllUserByMeetingId(Integer meetingId);


}
