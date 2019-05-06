package com.winter.controller;


import com.winter.common.ServerResponse;
import com.winter.domain.MeetingVote;
import com.winter.service.IVoteService;
import com.winter.vo.VoteVo;
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

    /**
     * 新建一个投票
     * @param meetingVote
     * @param options
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ServerResponse createVote(MeetingVote meetingVote, String[] options) {
        if (meetingVote == null || options == null || options.length < 2 || options.length > 5) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        System.out.println(options);
        return voteService.createVote(meetingVote,options);
    }

    /**
     * 用户进行投票
     * @param userId
     * @param voteId
     * @param optionIds
     * @return
     */
    @RequestMapping(value = "/userOption",method = RequestMethod.POST)
    public ServerResponse userCommitOption(Integer userId, Integer voteId, Integer[] optionIds) {
        boolean hasVoted = voteService.checkUserHasVoted(userId, voteId);
        if (hasVoted) {
            return ServerResponse.createByErrorMessage("您已经投过票!");
        }
        return voteService.userCommitOption(userId,voteId,optionIds);
    }

    /**
     * 根据会议id获取所有投票信息
     * @param meetingId
     * @return
     */
    @RequestMapping(value = "/specificInfo",method = RequestMethod.GET)
    public ServerResponse<List<VoteVo>> getMeetingVoteInfo(Integer meetingId, Integer userId) {
        if (meetingId == null || userId == null) {
            return ServerResponse.createByErrorMessage("参数错误!");
        }
        return voteService.getMeetingVoteInfo(meetingId,userId);
    }


}
