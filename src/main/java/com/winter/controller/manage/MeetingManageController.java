package com.winter.controller.manage;

import com.winter.common.ServerResponse;
import com.winter.service.IMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("meetingManage")
public class MeetingManageController {

    private IMeetingService meetingService;

    @Autowired
    public void setMeetingService(IMeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @RequestMapping(value = "deleteMeeting",method = RequestMethod.POST)
    public ServerResponse deleteMeeting(Integer meetingId) {
        if (meetingId == null) {
            return ServerResponse.createByErrorMessage("参数错误!");
        }
        return meetingService.deleteMeeting(meetingId);
    }


}
