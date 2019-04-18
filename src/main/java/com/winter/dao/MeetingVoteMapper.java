package com.winter.dao;

import com.winter.domain.MeetingVote;

public interface MeetingVoteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MeetingVote record);

    int insertSelective(MeetingVote record);

    MeetingVote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MeetingVote record);

    int updateByPrimaryKey(MeetingVote record);
}