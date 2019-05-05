package com.winter.service;

import com.winter.common.ServerResponse;
import com.winter.domain.MeetingVote;
import com.winter.vo.VoteVo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface IVoteService {

    ServerResponse createVote(MeetingVote meetingVote, String[] options);

    boolean addOptions(Integer id, String[] options);

    boolean checkUserHasVoted(Integer userId, Integer voteId);

    ServerResponse userCommitOption(Integer userId, Integer voteId, Integer[] optionIds);

    ServerResponse<List<VoteVo>> getMeetingVoteInfo(Integer meetingId, Integer userId);

    List<Integer> checkNoticeMeeting();

    List<Integer> checkNoticePeople(List<Integer> noticeMeetingIds);
}
