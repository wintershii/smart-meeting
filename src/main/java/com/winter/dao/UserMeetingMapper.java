package com.winter.dao;

import com.winter.domain.UserMeeting;
import com.winter.vo.UserStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMeetingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserMeeting record);

    int insertSelective(UserMeeting record);

    UserMeeting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserMeeting record);

    int updateByPrimaryKey(UserMeeting record);

    int getPeopleNum(Integer meetingId);

    List<UserStatus> getUserStatus(Integer meetingId);

    Integer getOneStatusByUserAndMeeting(@Param("userId") Integer userId,@Param("meetingId") Integer meetingId);

    int checkExist(@Param("userId") Integer userId, @Param("meetingId") Integer meetingId);

    int uploadUserMeetingStatus(@Param("userId") Integer userId, @Param("meetingId") Integer meetingId,
                                @Param("userStatus") Integer userStatus);
    int deleteByMeetingId(Integer meetingId);

    int applyLeave(@Param("userId") Integer userId, @Param("meetingId") Integer meetingId);
}