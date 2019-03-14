package com.winter.service;

import com.winter.domain.Meeting;
import com.winter.domain.Room;

import java.util.Date;
import java.util.List;

public interface IRoomService {
    List<Room> getAllRooms();
    List<Meeting> getMeetingsByRoomId(Integer roomId, boolean flag);

    Room getRoomById(Integer roomId);
}
