package com.winter.service.impl;

import com.winter.dao.MeetingMapper;
import com.winter.dao.RoomMapper;
import com.winter.dao.UserMeetingMapper;
import com.winter.domain.Meeting;
import com.winter.domain.Room;
import com.winter.service.IRoomService;
import com.winter.vo.RoomVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements IRoomService {

    private RoomMapper roomMapper;

    private MeetingMapper meetingMapper;

    @Autowired
    public void setRoomMapper(RoomMapper roomMapper) {
        this.roomMapper = roomMapper;
    }

    @Autowired
    public void setMeetingMapper(MeetingMapper meetingMapper) {
        this.meetingMapper = meetingMapper;
    }

    /**
     * 获取所有会议室数据
     * @return
     */
    @Override
    public List<Room> getAllRooms() {
        return roomMapper.selectAll();
    }

    /**
     * 根据会议室id获取会议列表
     * @param roomId
     * @return
     */
    @Override
    public List<Meeting> getMeetingsByRoomId(Integer roomId, boolean flag) {
        if (flag) {
            //最近五天记录
            return meetingMapper.getMeetingByRoomId(roomId,1);
        }
        //所有数据
        return meetingMapper.getMeetingByRoomId(roomId,null);
    }

    /**
     * 根据房间id获取信息
     * @param roomId
     * @return
     */
    @Override
    public Room getRoomById(Integer roomId) {
        return roomMapper.selectByPrimaryKey(roomId);
    }

}
