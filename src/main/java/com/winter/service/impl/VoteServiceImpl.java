package com.winter.service.impl;

import com.winter.common.ServerResponse;
import com.winter.dao.MeetingVoteMapper;
import com.winter.domain.MeetingVote;
import com.winter.service.IVoteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VoteServiceImpl implements IVoteService {

    private MeetingVoteMapper meetingVoteMapper;

    @Autowired
    public void setMeetingVoteMapper(MeetingVoteMapper meetingVoteMapper) {
        this.meetingVoteMapper = meetingVoteMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServerResponse createVote(MeetingVote meetingVote, List<String> options) {
        try {
            int resultCount = meetingVoteMapper.insert(meetingVote);
            if (resultCount > 0) {
                Integer voteId = meetingVote.getId();
                if (voteId == null) {
                    throw new Exception("未获取到投票项目id");
                }
                if (addOptions(voteId, options)) {
                    return ServerResponse.createBySuccess("创建投票成功!");
                } else {
                    throw new Exception("投票选项未创建成功");
                }
            }
            return ServerResponse.createByErrorMessage("创建投票项目失败!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createByErrorMessage("创建投票过程发生错误");
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public boolean addOptions(Integer id, List<String> options) {
        for (String option : options) {
            if (StringUtils.isNotBlank(option)) {
                int resultCount = meetingVoteMapper.addOption(id,option);
                if (resultCount == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServerResponse userCommitOption(Integer userId, Integer voteId, List<Integer> optionIds) {
        for (Integer option : optionIds) {
            if (option != null) {
                int resultCount = meetingVoteMapper.userCommitOption(userId,voteId,option);
                if (resultCount == 0) {
                    try {
                        throw new Exception();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return ServerResponse.createByErrorMessage("投票失败");
                }
            }
        }
        return ServerResponse.createBySuccess("投票成功");
    }
}
