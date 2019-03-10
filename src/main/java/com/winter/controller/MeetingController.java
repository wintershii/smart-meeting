package com.winter.controller;

import com.winter.common.ServerResponse;
import com.winter.service.IMeetingService;
import com.winter.vo.MeetingVo;
import com.winter.vo.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public ServerResponse<List<MeetingVo>> getUserMeetings(Integer userId, Integer type){
        List<MeetingVo> meetingList = meetingService.getUserMeetings(userId,type);
        if (meetingList != null) {
            return ServerResponse.createBySuccess(meetingList);
        }
        return ServerResponse.createByErrorMessage("获取会议信息失败!");
    }

    @ResponseBody
    @RequestMapping(value = "/getMeetingById.do",method = RequestMethod.POST)
    public ServerResponse<MeetingVo> getMeetingById(Integer meetingId) {
        MeetingVo meetingVo = meetingService.getMeetingById(meetingId);
        System.out.println(meetingVo);
        if (meetingVo != null) {
            List<UserStatus> userStatus = meetingService.getUserStatus(meetingId);
            System.out.println(userStatus);
            meetingVo.setMemberStatus(userStatus);
            return ServerResponse.createBySuccess(meetingVo);
        }
        return ServerResponse.createByErrorMessage("获取会议信息失败");
    }


}
