package com.winter.controller.access;

import com.sun.org.apache.regexp.internal.RE;
import com.winter.common.ServerResponse;
import com.winter.domain.Meeting;
import com.winter.domain.Room;
import com.winter.service.IMeetingService;
import com.winter.service.IRoomService;
import com.winter.service.IUserMeetingService;
import com.winter.service.IUserService;
import com.winter.vo.AccessRoomVo;
import com.winter.vo.MeetingVo;
import com.winter.vo.RoomVo;
import com.winter.vo.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.util.List;

@Controller
@RequestMapping("/access")
public class AccessMeetingRoomController {

    private IRoomService roomService;

    private IMeetingService meetingService;

    private IUserMeetingService userMeetingService;

    private IUserService userService;

    @Autowired
    public void setRoomService(IRoomService roomService) {
        this.roomService = roomService;
    }

    @Autowired
    public void setMeetingService(IMeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @Autowired
    public void setUserMeetingService(IUserMeetingService userMeetingService) {
        this.userMeetingService = userMeetingService;
    }

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 验证会议室门牌号与需要绑定的设备是否是对应关系
     * @param roomNumber
     * @param machineNumber
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkMapping.do",method = RequestMethod.POST)
    public ServerResponse checkMapping(String roomNumber, String machineNumber) {
        return roomService.checkMapping(roomNumber,machineNumber);
    }

    /**
     * 根据会议室门牌号获取会议室信息
     * @param roomNumber
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getInfoByRoomNumber.do",method = RequestMethod.GET)
    public ServerResponse<AccessRoomVo> getInfoByRoomNumber(String roomNumber) {
        return roomService.getInfoByRoomNumber(roomNumber);
    }

    /**
     * 根据会议id获取与会人员id,名字,人脸数据(faceData)
     * @param meetingId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllUserByMeetingId.do",method = RequestMethod.GET)
    public ServerResponse getAllUserByMeetingId(Integer meetingId) {
        return meetingService.getAllUserByMeetingId(meetingId);
    }

    /**
     * 根据会议id获取与会人员的会议状态
     * @param meetingId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllUserStatus.do",method = RequestMethod.GET)
    public ServerResponse<List<UserStatus>> getAllUserStatus(Integer meetingId) {
        List<UserStatus> list = meetingService.getUserStatus(meetingId);
        if (list != null) {
            return ServerResponse.createBySuccess(list);
        }
        return ServerResponse.createByErrorMessage("获取用户信息失败");
    }

    /**
     * 更新与会人员的参与会议状态
     * @param userId
     * @param meetingId
     * @param userStatus
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/uploadUserMeetingStatus.do",method = RequestMethod.POST)
    public ServerResponse uploadUserMeetingStatus(Integer userId, Integer meetingId, Integer userStatus) {
        return userMeetingService.uploadUserMeetingStatus(userId,meetingId,userStatus);
    }


}
