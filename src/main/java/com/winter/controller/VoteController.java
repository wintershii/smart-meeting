package com.winter.controller;


import com.winter.common.ServerResponse;
import com.winter.domain.MeetingVote;
import com.winter.service.IVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/vote")
public class VoteController {

    private IVoteService voteService;

    @Autowired
    public void setVoteService(IVoteService voteService) {
        this.voteService = voteService;
    }

    public ServerResponse createVote(MeetingVote meetingVote, List<String> options) {
        meetingVote.setCreateTime(new Date());
        ServerResponse createVoteResponse = voteService.createVote(meetingVote);
        if (createVoteResponse.isSuccess()) {

        }
    }
}
