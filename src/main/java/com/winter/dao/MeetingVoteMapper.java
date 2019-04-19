package com.winter.dao;

import com.winter.domain.MeetingVote;
import org.apache.ibatis.annotations.Param;

public interface MeetingVoteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MeetingVote record);

    int insertSelective(MeetingVote record);

    MeetingVote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MeetingVote record);

    int updateByPrimaryKey(MeetingVote record);

    int addOption(@Param("id") Integer id, @Param("option") String option);

    int userCommitOption(@Param("userId") Integer userId, @Param("voteId") Integer voteId, @Param("optionId") Integer optionId);
}