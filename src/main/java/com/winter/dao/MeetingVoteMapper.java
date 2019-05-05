package com.winter.dao;

import com.winter.domain.MeetingVote;
import com.winter.vo.VoteOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MeetingVoteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MeetingVote record);

    int insertSelective(MeetingVote record);

    MeetingVote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MeetingVote record);

    int updateByPrimaryKey(MeetingVote record);

    int addOption(@Param("id") Integer id, @Param("option") String option);

    int userCommitOption(@Param("userId") Integer userId, @Param("voteId") Integer voteId, @Param("optionId") Integer optionId);

    List<MeetingVote> getAllMeetingVote(Integer meetingId);

    List<VoteOption> getVoteOption(Integer voteId);

    List<Integer> getUserSelectList(@Param("voteId") Integer voteId, @Param("userId") Integer userId);

    int increaseOption(Integer optionId);

    int checkUserHasVoted(@Param("userId") Integer userId, @Param("voteId") Integer voteId);

    List<Integer> selectVoteIdByMeetingId(Integer meetingId);

    int deleteOptionsByVoteId(Integer voteId);

    int deleteUserOptionByVoteId(Integer voteId);

    List<Integer> getNoticeMeetingIds();
}