package com.winter.service;

import com.winter.domain.Room;
import com.winter.domain.UserMeeting;

import java.util.List;

public interface IRoomService {
    List<Room> getAllRooms();
    List<UserMeeting> getMeetingsByRoomId(Integer roomId, boolean flag);
}
