package com.winter.dao;

import com.winter.domain.UserMeeting;
import com.winter.vo.UserStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMeetingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserMeeting record);

    int insertSelective(UserMeeting record);

    UserMeeting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserMeeting record);

    int updateByPrimaryKey(UserMeeting record);

    List<UserMeeting> getUserMeetingsOngoing(Integer userId);

    List<UserMeeting> getUserMeetingsFinished(Integer userId);


    int getPeopleNum(Integer meetingName);

    List<UserStatus> getUserStatus(Integer meetingId);

    List<UserMeeting> getMeetingIdByRoomId(@Param("roomId") Integer roomId, @Param("flag") Integer flag);
}