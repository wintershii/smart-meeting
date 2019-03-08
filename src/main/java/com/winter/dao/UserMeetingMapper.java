package com.winter.dao;

import com.winter.domain.UserMeeting;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMeetingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserMeeting record);

    int insertSelective(UserMeeting record);

    UserMeeting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserMeeting record);

    int updateByPrimaryKey(UserMeeting record);

    List<UserMeeting> getUserMeetings(@Param("userId") Integer userId, @Param("type") Integer type);
}