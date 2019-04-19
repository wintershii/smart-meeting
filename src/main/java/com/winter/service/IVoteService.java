package com.winter.service;

import com.winter.common.ServerResponse;
import com.winter.domain.MeetingVote;

import java.util.List;

public interface IVoteService {

    ServerResponse createVote(MeetingVote meetingVote, List<String> options);

    boolean addOptions(Integer id, List<String> options);

    ServerResponse userCommitOption(Integer userId, Integer voteId, List<Integer> optionIds);
}
