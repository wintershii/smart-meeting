package com.winter.service;

import com.winter.domain.Room;

import java.util.List;

public interface IRoomService {
    List<Room> getAllRooms();
    List<Integer> getMeetingsByRoomId(Integer roomId,boolean flag);
}
