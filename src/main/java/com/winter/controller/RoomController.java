package com.winter.controller;

import com.winter.common.ServerResponse;
import com.winter.domain.Room;
import com.winter.domain.UserMeeting;
import com.winter.service.IRoomService;
import com.winter.vo.RoomVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/room")
public class RoomController {

    private IRoomService roomService;

    @Autowired
    public void setRoomService(IRoomService roomService) {
        this.roomService = roomService;
    }

    @ResponseBody
    @RequestMapping(value = "/getAllRooms.do",method = RequestMethod.POST)
    public ServerResponse<List<RoomVo>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        List<RoomVo> roomVoList = roomToVo(rooms);
        if (roomVoList != null) {
            return ServerResponse.createBySuccess(roomVoList);
        }
        return ServerResponse.createByErrorMessage("获取会议室信息失败");
    }


    /**
     * 将room对象转为roomVo对象
     * @param rooms
     * @return
     */
    private List<RoomVo> roomToVo(List<Room> rooms) {
        if (rooms == null || rooms.size() == 0) {
            return null;
        }
        List<RoomVo> list = new ArrayList<>();
        for (Room room : rooms) {
            RoomVo roomVo = new RoomVo();
            roomVo.setId(room.getId());
            roomVo.setRoomNumber(room.getRoomNumber());
            roomVo.setMachineNumber(room.getMachineNumber());
            roomVo.setStatus(room.getStatus());
            List<UserMeeting> meetingLists = roomService.getMeetingsByRoomId(room.getId(),false);
            List<UserMeeting> recentlyMeetings = roomService.getMeetingsByRoomId(room.getId(),true);

            roomVo.setMeetingLists(meetingLists);
            roomVo.setRecentlyMeetings(recentlyMeetings);

            list.add(roomVo);
        }
        return list;
    }

}
