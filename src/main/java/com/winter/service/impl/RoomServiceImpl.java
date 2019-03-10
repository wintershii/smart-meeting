package com.winter.service.impl;

import com.winter.dao.RoomMapper;
import com.winter.dao.UserMeetingMapper;
import com.winter.domain.Room;
import com.winter.domain.UserMeeting;
import com.winter.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements IRoomService {

    private RoomMapper roomMapper;

    private UserMeetingMapper meetingMapper;

    @Autowired
    public void setRoomMapper(RoomMapper roomMapper) {
        this.roomMapper = roomMapper;
    }

    @Autowired
    public void setMeetingMapper(UserMeetingMapper meetingMapper) {
        this.meetingMapper = meetingMapper;
    }

    /**
     * 获取所有会议室数据
     * @return
     */
    public List<Room> getAllRooms() {
        return roomMapper.selectAll();
    }

    /**
     * 根据会议室id获取会议id列表
     * @param roomId
     * @return
     */
    public List<UserMeeting> getMeetingsByRoomId(Integer roomId, boolean flag) {
        if (flag) {
            //最近五天记录
            return meetingMapper.getMeetingIdByRoomId(roomId,1);
        }
        //所有数据
        return meetingMapper.getMeetingIdByRoomId(roomId,null);
    }
}
