package com.winter.vo;

import com.winter.domain.UserMeeting;

import java.util.List;

public class RoomVo {
    private Integer Id;
    private String roomNumber;
    private String machineNumber;
    private Integer status;
    private List<UserMeeting> meetingLists;
    private List<UserMeeting> recentlyMeetings;

    public RoomVo() {
    }


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<UserMeeting> getMeetingLists() {
        return meetingLists;
    }

    public void setMeetingLists(List<UserMeeting> meetingLists) {
        this.meetingLists = meetingLists;
    }

    public List<UserMeeting> getRecentlyMeetings() {
        return recentlyMeetings;
    }

    public void setRecentlyMeetings(List<UserMeeting> recentlyMeetings) {
        this.recentlyMeetings = recentlyMeetings;
    }
}
