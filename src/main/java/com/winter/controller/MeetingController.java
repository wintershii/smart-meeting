package com.winter.controller;

import com.github.pagehelper.PageInfo;
import com.winter.common.Const;
import com.winter.common.ServerResponse;
import com.winter.domain.Meeting;
import com.winter.domain.UserMeeting;
import com.winter.service.IMeetingService;
import com.winter.service.IUserMeetingService;
import com.winter.util.TokenUtil;
import com.winter.vo.MeetingVo;
import com.winter.vo.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/meeting")
public class MeetingController {


    private IMeetingService meetingService;

    private IUserMeetingService userMeetingService;


    @Autowired
    public void setMeetingService(IMeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @Autowired
    public void setUserMeetingService(IUserMeetingService userMeetingService) {
        this.userMeetingService = userMeetingService;
    }

    /**
     * 获取用户参加的会议信息(根据用户id)
     * @param userId
     * @param type
     * @return
     */
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

    /**
     * 根据会议id获取会议相关信息
     * @param meetingId
     * @return
     */
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

    /**
     * 分页获取会议信息(根据会议室id)
     * @param roomId
     * @param page
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getPageMeetingInfo.do",method = RequestMethod.POST)
    public ServerResponse<PageInfo> getPageMeetingInfo(Integer roomId,Integer page) {
        PageInfo list = meetingService.getPageMeetings(roomId,page);
        return ServerResponse.createBySuccess(list);
    }

    /**
     * 根据会议室id和预定时间段,判断是否可以预定
     * @param roomId
     * @param startTime
     * @param endTime
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/whetherBook.do",method = RequestMethod.POST)
    public ServerResponse whetherBook(Integer roomId, Date startTime, Date endTime) {
        int resultCount = meetingService.whetherBook(roomId,startTime,endTime);
        if (resultCount == 0) {
            return ServerResponse.createBySuccess("可以预约此会议室");
        }
        return ServerResponse.createByErrorMessage("无法预约此会议室");
    }


    /**
     * 填写会议信息,预定会议(涉及事务)
     * @param meeting
     * @return
     */
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

    /**
     * 取消会议,设置会议状态
     * @param meetingId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cancelBook.do",method = RequestMethod.POST)
    public ServerResponse cancelBook(Integer meetingId, HttpServletRequest request) {
        Integer masterId = meetingService.getMeetingMasterId(meetingId);
        String token = request.getHeader("token");
        Integer tokenId = Integer.parseInt(TokenUtil.getInfo(token,"id"));
        if (masterId != null && masterId.intValue() == tokenId.intValue()) {
            int resultCount = meetingService.setMeetingStatus(meetingId,Const.MeetingStatus.CANCEL);
            if (resultCount > 0) {
                return ServerResponse.createBySuccessMessage("取消会议成功!");
            }
            return ServerResponse.createByErrorMessage("取消会议失败!");
        }
        return ServerResponse.createByErrorMessage("无权限操作!");
    }

    /**
     * 根据用户id,邀请成员加入会议
     * @param userId
     * @param meetingId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/inviteMeetingMember.do",method = RequestMethod.POST)
    public ServerResponse inviteMeetingMember(Integer userId,Integer meetingId) {
        UserMeeting userMeeting = new UserMeeting();
        userMeeting.setUserId(userId);
        userMeeting.setMeetingId(meetingId);
        userMeeting.setUserStatus(Const.UserPerform.ABSENCE);
        if (userMeetingService.checkExist(userId,meetingId)) {
            int resultCount = userMeetingService.inviteMeetingMember(userMeeting);
            if (resultCount > 0) {
                return ServerResponse.createBySuccessMessage("邀请会议成员成功!");
            }
            return ServerResponse.createByErrorMessage("邀请会议成员失败");
        }
        return ServerResponse.createByErrorMessage("该成员已在会议中!");
    }

    /**
     * 开始会议
     * @param meetingId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/startMeeting.do",method = RequestMethod.POST)
    public ServerResponse startMeeting(Integer meetingId,HttpServletRequest request) {
        Integer masterId = meetingService.getMeetingMasterId(meetingId);
        String token = request.getHeader("token");
        Integer tokenId = Integer.parseInt(TokenUtil.getInfo(token,"id"));
        if (masterId != null && masterId.intValue() == tokenId.intValue()) {
            int resultCount = meetingService.setMeetingStatus(meetingId,Const.MeetingStatus.ONGOING);
            if (resultCount > 0) {
                return ServerResponse.createBySuccessMessage("会议成功开始");
            }
            return ServerResponse.createByErrorMessage("会议开始失败");
        }
        return ServerResponse.createByErrorMessage("无权限操作!");
    }

    /**
     * 结束会议
     * @param meetingId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/endMeeting.do",method = RequestMethod.POST)
    public ServerResponse endMeeting(Integer meetingId,HttpServletRequest request) {
        Integer masterId = meetingService.getMeetingMasterId(meetingId);
        String token = request.getHeader("token");
        Integer tokenId = Integer.parseInt(TokenUtil.getInfo(token,"id"));
        if (masterId != null && masterId.intValue() == tokenId.intValue()) {
            int resultCount = meetingService.setMeetingStatus(meetingId,Const.MeetingStatus.OVER);
            if (resultCount > 0) {
                return ServerResponse.createBySuccessMessage("会议结束");
            }
            return ServerResponse.createByErrorMessage("会议结束失败");
        }
        return ServerResponse.createByErrorMessage("无权限操作!");
    }


}
