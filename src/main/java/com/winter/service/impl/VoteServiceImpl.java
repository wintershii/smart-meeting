package com.winter.service.impl;

import com.winter.common.ServerResponse;
import com.winter.dao.MeetingMapper;
import com.winter.dao.MeetingVoteMapper;
import com.winter.dao.UserMapper;
import com.winter.domain.Meeting;
import com.winter.domain.MeetingVote;
import com.winter.service.IVoteService;
import com.winter.vo.MeetingVo;
import com.winter.vo.UserAvatarInfo;
import com.winter.vo.VoteOption;
import com.winter.vo.VoteVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class VoteServiceImpl implements IVoteService {

    private MeetingVoteMapper meetingVoteMapper;

    private UserMapper userMapper;

    private MeetingMapper meetingMapper;

    @Autowired
    public void setMeetingVoteMapper(MeetingVoteMapper meetingVoteMapper) {
        this.meetingVoteMapper = meetingVoteMapper;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setMeetingMapper(MeetingMapper meetingMapper) {
        this.meetingMapper = meetingMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServerResponse createVote(MeetingVote meetingVote, String[] options) {
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
    public boolean addOptions(Integer id, String[] options) {
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
    public ServerResponse userCommitOption(Integer userId, Integer voteId, Integer[] optionIds) {
        for (Integer option : optionIds) {
            if (option != null) {
                int resultCount = meetingVoteMapper.userCommitOption(userId,voteId,option);
                int increaseCount = meetingVoteMapper.increaseOption(option);
                if (resultCount == 0 || increaseCount == 0) {
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


    @Override
    public ServerResponse<List<VoteVo>> getMeetingVoteInfo(Integer meetingId, Integer userId) {
        List<MeetingVote> meetingVoteList = meetingVoteMapper.getAllMeetingVote(meetingId);
        if (meetingVoteList == null) {
            return ServerResponse.createByErrorMessage("查询失败");
        }
        List<VoteVo> voList = meetingVoteToVo(meetingVoteList,userId);
        if (voList != null) {
            return ServerResponse.createBySuccess(voList);
        }
        return ServerResponse.createByErrorMessage("查询失败!");
    }


    @Override
    public boolean checkUserHasVoted(Integer userId, Integer voteId) {
        int resultCount = meetingVoteMapper.checkUserHasVoted(userId,voteId);
        if (resultCount > 0) {
            return true;
        }
        return false;
    }


    @Override
    public List<Integer> checkNoticeMeeting() {
        List<Integer> noticeVoteIds = meetingVoteMapper.getNoticeMeetingIds();
        return noticeVoteIds;
    }

    @Override
    public List<Integer> checkNoticePeople(List<Integer> noticeMeetingIds) {
        List<Integer> peopleList = new ArrayList<>();
        for (Integer meetingId : noticeMeetingIds) {
            peopleList.addAll(meetingMapper.getPeopleIds(meetingId));
        }
        return peopleList;
    }

    private List<VoteVo> meetingVoteToVo(List<MeetingVote> meetingVotes, Integer userId) {
        List<VoteVo> voList = new ArrayList<>();
        for (MeetingVote meetingVote: meetingVotes) {
            VoteVo vo = meetingVoteToVo(meetingVote,userId);
            if (vo != null) {
                voList.add(vo);
            }
        }
        return voList;
    }

    private VoteVo meetingVoteToVo(MeetingVote meetingVote,Integer userId) {
        VoteVo voteVo = new VoteVo();
        Integer voteId = meetingVote.getId();
        Integer publisherId = meetingVote.getPublisherId();
        voteVo.setVoteId(voteId);
        voteVo.setTopic(meetingVote.getTopic());
        voteVo.setSelectWay(meetingVote.getSelectWay());
        voteVo.setCreateTime(meetingVote.getCreateTime());
        voteVo.setEndTime(meetingVote.getEndTime());

        List<VoteOption> voteOptionList = meetingVoteMapper.getVoteOption(voteId);
        UserAvatarInfo userInfo = userMapper.getUserAvatarInfo(publisherId);
        voteVo.setOptionList(voteOptionList);
        voteVo.setUserInfo(userInfo);

        List<Integer> userSelectList = meetingVoteMapper.getUserSelectList(voteId, userId);
        voteVo.setUserSelectList(userSelectList);

        return voteVo;
    }
}
