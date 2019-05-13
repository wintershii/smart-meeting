package com.winter.service;

import com.winter.common.ServerResponse;
import com.winter.domain.Meeting;
import com.winter.domain.Room;
import com.winter.vo.AccessRoomVo;
import com.winter.vo.RoomVo;
import com.winter.vo.UserAccessInfo;

import java.util.Date;
import java.util.List;

public interface IRoomService {
    ServerResponse<List<RoomVo>> getAllRooms();
    List<Meeting> getMeetingsByRoomId(Integer roomId, boolean flag);

    RoomVo getRoomById(Integer roomId);


    ServerResponse checkMapping(String roomNumber, String machineNumber);

    ServerResponse<AccessRoomVo> getInfoByRoomNumber(String roomNumber);

    int setRoomStatus(Integer roomId, int roomStatus);

    ServerResponse bindAccessMachineNumber(String roomNumber, String machineNumber);

    ServerResponse addMeetingRoom(Room room);

    ServerResponse deleteRoom(Integer roomId);

    ServerResponse updateRoom(Room room);

    ServerResponse<Integer> getIdByRoom(String roomNumber);
}
