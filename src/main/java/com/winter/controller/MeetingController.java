package com.winter.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winter.common.Const;
import com.winter.common.ServerResponse;
import com.winter.domain.Meeting;
import com.winter.service.IMeetingService;
import com.winter.service.IRoomService;
import com.winter.util.TokenUtil;
import com.winter.vo.MeetingVo;
import com.winter.vo.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/meeting")
public class MeetingController {


    private IMeetingService meetingService;

    private IRoomService roomService;


    @Autowired
    public void setMeetingService(IMeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @Autowired
    public void setRoomService(IRoomService roomService) {
        this.roomService = roomService;
    }

    @ResponseBody
    @RequestMapping(value = "/getUserMeetings.do",method = RequestMethod.POST)
    public ServerResponse<List<MeetingVo>> getUserMeetings(Integer userId, Integer type){
        //type: 1--用户正在参加或还未参加的会议  2--用户已经参加过的会议
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


    @ResponseBody
    @RequestMapping(value = "/getPageMeetingInfo.do",method = RequestMethod.POST)
    public ServerResponse<PageInfo> getPageMeetingInfo(Integer roomId,Integer page) {
        PageInfo list = meetingService.getPageMeetings(roomId,page);
        return ServerResponse.createBySuccess(list);
    }

    @ResponseBody
    @RequestMapping(value = "/whetherBook.do",method = RequestMethod.POST)
    public ServerResponse whetherBook(Integer roomId, Date startTime, Date endTime) {
        int resultCount = meetingService.whetherBook(roomId,startTime,endTime);
        if (resultCount == 0) {
            return ServerResponse.createBySuccess("可以预约此会议室");
        }
        return ServerResponse.createByErrorMessage("无法预约此会议室");
    }


    @ResponseBody
    @RequestMapping(value = "/bookMeeting.do",method = RequestMethod.POST)
    public ServerResponse<Meeting> bookMeeting(Meeting meeting) {
        //设置会议状态为未开始
        meeting.setStatus(Const.MeetingStatus.NOTSTART);
        int resultCount = meetingService.bookMeeting(meeting);
        if (resultCount > 0) {
            return ServerResponse.createBySuccess(meeting);
        }
        return ServerResponse.createByErrorMessage("预定失败");
    }

    @ResponseBody
    @RequestMapping(value = "/cancelBook.do",method = RequestMethod.POST)
    public ServerResponse cancelBook(Integer meetingId, HttpServletRequest request) {
        Integer masterId = meetingService.getMeetingMasterId(meetingId);
        String token = request.getHeader("token");
        Integer tokenId = Integer.parseInt(TokenUtil.getInfo(token,"id"));
        if (masterId != null && masterId.intValue() == tokenId.intValue()) {
            int resultCount = meetingService.cancelBook(meetingId);
            if (resultCount > 0) {
                return ServerResponse.createBySuccessMessage("取消会议成功!");
            }
            return ServerResponse.createByErrorMessage("取消会议失败!");
        }
        return ServerResponse.createByErrorMessage("无权限操作!");
    }

}
