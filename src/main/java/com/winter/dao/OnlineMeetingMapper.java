package com.winter.dao;

import com.winter.domain.OnlineMeeting;

import java.util.List;

public interface OnlineMeetingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OnlineMeeting record);

    int insertSelective(OnlineMeeting record);

    OnlineMeeting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OnlineMeeting record);

    int updateByPrimaryKey(OnlineMeeting record);

    int updateStatus(Integer liveId);

    int updateOnlinePeopleNum(Integer liveId);

    List<OnlineMeeting> selectAll();

    String getPassword(Integer liveId);

    int checkStatus(Integer liveId);
}