package com.winter.controller;

import com.winter.common.ServerResponse;
import com.winter.domain.UserMeeting;
import com.winter.service.IMeetingService;
import com.winter.service.IUserService;
import com.winter.util.DateTimeUtil;
import com.winter.vo.MeetingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/meeting")
public class MeetingController {


    private IMeetingService meetingService;


    @Autowired
    public void setMeetingService(IMeetingService meetingService) {
        this.meetingService = meetingService;
    }


    @ResponseBody
    @RequestMapping(value = "/getUserMeetings.do",method = RequestMethod.POST)
    public ServerResponse<List<MeetingVo>> getUserMeetings(Integer userId, int type){
        List<MeetingVo> meetingList;
        meetingList = meetingService.getUserMeetings(userId,type);
        if (meetingList != null) {
            return ServerResponse.createBySuccess(meetingList);
        }
        return ServerResponse.createByErrorMessage("获取会议信息失败!");
    }

    @ResponseBody
    @RequestMapping(value = "/getMeetingById.do",method = RequestMethod.POST)
    public ServerResponse<MeetingVo> getMeetingById(Integer meetingId) {
        MeetingVo meetingVo = meetingService.getMeetingById(meetingId);
        if (meetingVo != null) {
            Map<Integer,Integer> userStatus = meetingService.getUserStatus(meetingId);
            meetingVo.setMemberStatus(userStatus);
            return ServerResponse.createBySuccess(meetingVo);
        }
        return ServerResponse.createByErrorMessage("获取会议信息失败");
    }


}
