package com.winter.service.impl;

import com.winter.dao.MeetingVoteMapper;
import com.winter.service.IVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements IVoteService {

    private MeetingVoteMapper meetingVoteMapper;

    @Autowired
    public void setMeetingVoteMapper(MeetingVoteMapper meetingVoteMapper) {
        this.meetingVoteMapper = meetingVoteMapper;
    }
}
