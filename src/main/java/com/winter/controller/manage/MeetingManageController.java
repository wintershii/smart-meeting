package com.winter.controller.manage;

import com.winter.common.Const;
import com.winter.common.ServerResponse;
import com.winter.domain.Room;
import com.winter.service.IMeetingService;
import com.winter.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping("/meetingManage")
public class MeetingManageController {

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

    @RequestMapping(value = "/deleteMeeting",method = RequestMethod.DELETE)
    public ServerResponse deleteMeeting(Integer meetingId) {
        if (meetingId == null) {
            return ServerResponse.createByErrorMessage("参数错误!");
        }
        return meetingService.deleteMeeting(meetingId);
    }


    @RequestMapping(value = "/addRoom",method = RequestMethod.POST)
    public ServerResponse addMeetingRoom(Room room) {
        room.setStatus(Const.RoomStatus.FREE);
        return roomService.addMeetingRoom(room);
    }

    @RequestMapping(value = "/deleteRoom",method = RequestMethod.DELETE)
    public ServerResponse deleteRoom(Integer roomId) {
        if (roomId == null) {
            return ServerResponse.createByErrorMessage("参数错误!");
        }
        return roomService.deleteRoom(roomId);
    }

    @RequestMapping(value = "/updateRoom",method = RequestMethod.POST)
    public ServerResponse updateRoom(Room room) {
        return roomService.updateRoom(room);
    }

}
