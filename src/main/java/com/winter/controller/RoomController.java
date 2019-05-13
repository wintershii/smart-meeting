package com.winter.controller;

import com.winter.common.ServerResponse;
import com.winter.domain.Meeting;
import com.winter.domain.Room;
import com.winter.service.IRoomService;
import com.winter.vo.RoomVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    /**
     * 获取所有会议室的信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllRooms.do",method = RequestMethod.POST)
    public ServerResponse<List<RoomVo>> getAllRooms() {
        return roomService.getAllRooms();
    }


    /**
     * 获取指定id的会议室信息
     * @param roomId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getRoomById.do",method = RequestMethod.POST)
    public ServerResponse<RoomVo> getRoomById(Integer roomId) {
        if (roomId == null) {
            return ServerResponse.createByErrorMessage("参数无效!");
        }
        RoomVo roomVo = roomService.getRoomById(roomId);
        if (roomVo != null) {
            return ServerResponse.createBySuccess(roomVo);
        }
        return ServerResponse.createByErrorMessage("查询失败");
    }
    
    @ResponseBody
    @RequestMapping(value = "/getIdByRoom.do",method = RequestMethod.GET)
    public ServerResponse<Integer> getIdByRoom(String roomNumber) {
        if (StringUtils.isBlank(roomNumber)) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return roomService.getIdByRoom(roomNumber);
    }



}
