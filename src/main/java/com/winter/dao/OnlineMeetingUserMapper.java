package com.winter.dao;

import com.winter.domain.OnlineMeetingUser;

import java.util.List;

public interface OnlineMeetingUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OnlineMeetingUser record);

    int insertSelective(OnlineMeetingUser record);

    OnlineMeetingUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OnlineMeetingUser record);

    int updateByPrimaryKey(OnlineMeetingUser record);

    List<Integer> selectAllUsers(Integer liveId);
}