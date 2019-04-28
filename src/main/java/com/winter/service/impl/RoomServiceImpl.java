package com.winter.service.impl;

import com.sun.org.apache.regexp.internal.RE;
import com.winter.common.Const;
import com.winter.common.ServerResponse;
import com.winter.dao.MeetingMapper;
import com.winter.dao.RoomMapper;
import com.winter.dao.UserMeetingMapper;
import com.winter.domain.Meeting;
import com.winter.domain.Room;
import com.winter.service.IMeetingService;
import com.winter.service.IRoomService;
import com.winter.vo.AccessRoomVo;
import com.winter.vo.MeetingVo;
import com.winter.vo.RoomVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoomServiceImpl implements IRoomService {

    private RoomMapper roomMapper;

    private MeetingMapper meetingMapper;

    private IMeetingService meetingService;

    @Autowired
    public void setRoomMapper(RoomMapper roomMapper) {
        this.roomMapper = roomMapper;
    }

    @Autowired
    public void setMeetingMapper(MeetingMapper meetingMapper) {
        this.meetingMapper = meetingMapper;
    }

    @Autowired
    public void setMeetingService(IMeetingService meetingService) {
        this.meetingService = meetingService;
    }

    /**
     * 获取所有会议室数据
     * @return
     */
    @Override
    public ServerResponse<List<RoomVo>> getAllRooms() {
        List<Room> rooms =  roomMapper.selectAll();
        List<RoomVo> roomVoList = roomToVo(rooms);
        if (roomVoList != null) {
            return ServerResponse.createBySuccess(roomVoList);
        }
        return ServerResponse.createByErrorMessage("获取会议室信息失败");
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
    public RoomVo getRoomById(Integer roomId) {
        Room room = roomMapper.selectByPrimaryKey(roomId);
        RoomVo roomVo = roomToVo(room);
        return roomVo;
    }


    @Override
    public ServerResponse checkMapping(String roomNumber, String machineNumber) {
        int resultCount = roomMapper.checkMapping(roomNumber,machineNumber);
        if (resultCount > 0) {
            return ServerResponse.createBySuccessMessage("映射关系正确");
        }
        return ServerResponse.createByErrorMessage("映射关系错误");
    }

    @Override
    public ServerResponse<AccessRoomVo> getInfoByRoomNumber(String roomNumber) {
        Room room = roomMapper.getInfoByRoomNumber(roomNumber);
        RoomVo roomVo = roomToVo(room);

        if (roomVo == null) {
            return ServerResponse.createByErrorMessage("查找失败,请检查参数");
        }

        AccessRoomVo accessRoomVo = new AccessRoomVo();
        accessRoomVo.setId(roomVo.getId());
        accessRoomVo.setContent(roomVo.getContent());
        accessRoomVo.setMachineNumber(roomVo.getMachineNumber());
        accessRoomVo.setRoomNumber(roomVo.getRoomNumber());
        accessRoomVo.setStatus(roomVo.getStatus());

        List<Meeting> meetingList = meetingMapper.getAccessMeeting(roomVo.getId());
        List<MeetingVo> meetingVoList = meetingService.meetingToVo(meetingList);

        accessRoomVo.setMeetingLists(meetingVoList);
        return ServerResponse.createBySuccess(accessRoomVo);

    }

    @Override
    public int setRoomStatus(Integer roomId, int roomStatus) {
        int resultCount = 0;
        if (roomStatus == Const.RoomStatus.USE) {
            resultCount = roomMapper.setRoomUse(roomId);
        } else if (roomStatus == Const.RoomStatus.FREE) {
            resultCount = roomMapper.setRoomFree(roomId);
        }
        return resultCount;
    }


    @Override
    public ServerResponse bindAccessMachineNumber(String roomNumber, String machineNumber) {
        int resultCount = roomMapper.bindAccessMachineNumber(roomNumber,machineNumber);
        if (resultCount > 0) {
            return ServerResponse.createBySuccess("绑定成功!");
        }
        return ServerResponse.createByErrorMessage("绑定失败!");
    }

    @Override
    public ServerResponse addMeetingRoom(Room room) {
        int resultCount = roomMapper.insert(room);
        if (resultCount > 0) {
            return ServerResponse.createBySuccess("新增会议室成功!");
        }
        return ServerResponse.createByErrorMessage("新增会议室失败!");
    }

    @Override
    public ServerResponse deleteRoom(Integer roomId) {
        int resultCount = roomMapper.deleteByPrimaryKey(roomId);
        if (resultCount > 0) {
            return ServerResponse.createBySuccess("删除会议室信息成功");
        }
        return ServerResponse.createByErrorMessage("删除会议室信息失败");
    }

    @Override
    public ServerResponse updateRoom(Room room) {
        int resultCount = roomMapper.updateByPrimaryKeySelective(room);
        if (resultCount > 0) {
            return ServerResponse.createBySuccess("更新会议室信息成功");
        }
        return ServerResponse.createByErrorMessage("更新会议室信息失败");
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
            RoomVo roomVo = roomToVo(room);
            list.add(roomVo);
        }
        return list;
    }

    private RoomVo roomToVo(Room room) {
        if (room == null) {
            return null;
        }
        RoomVo roomVo = new RoomVo();
        roomVo.setId(room.getId());
        roomVo.setRoomNumber(room.getRoomNumber());
        roomVo.setMachineNumber(room.getMachineNumber());
        roomVo.setStatus(room.getStatus());
        roomVo.setContent(room.getContent());
        List<Meeting> meetingLists = getMeetingsByRoomId(room.getId(),false);
        List<Meeting> recentlyMeetings = getMeetingsByRoomId(room.getId(),true);

        roomVo.setMeetingLists(meetingLists);
        roomVo.setRecentlyMeetings(recentlyMeetings);
        return roomVo;
    }

}
