package com.winter.controller;


import com.winter.common.ServerResponse;
import com.winter.domain.MeetingVote;
import com.winter.service.IVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vote")
public class VoteController {

    private IVoteService voteService;

    @Autowired
    public void setVoteService(IVoteService voteService) {
        this.voteService = voteService;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ServerResponse createVote(MeetingVote meetingVote, List<String> options) {
        return voteService.createVote(meetingVote,options);
    }

    @RequestMapping(value = "/userOption",method = RequestMethod.POST)
    public ServerResponse userCommitOption(Integer userId, Integer voteId, List<Integer> optionIds) {
        return voteService.userCommitOption(userId,voteId,optionIds);
    }

    /**
     * 根据会议id获取所有投票简单信息
     * @param meetingId
     * @return
     */
    public ServerResponse<> getMeetingVoteInfo(Integer meetingId) {

    }

    /**
     * 根据投票Id获取具体的投票信息
     * @param voteId
     * @return
     */
    public ServerResponse<> getSpecificVote(Integer voteId) {

    }

}
