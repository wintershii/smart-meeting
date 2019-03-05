package com.winter.vo;

import java.util.List;

public class RoomVo {
    private Integer Id;
    private String roomNumber;
    private String machineNumber;
    private Integer status;
    private List<Integer> meetingLists;
    private List<Integer> recentlyMeetings;

    public RoomVo() {
    }

    public RoomVo(Integer id, String roomNumber, String machineNumber, Integer status, List<Integer> meetingLists, List<Integer> recentlyMeetings) {
        Id = id;
        this.roomNumber = roomNumber;
        this.machineNumber = machineNumber;
        this.status = status;
        this.meetingLists = meetingLists;
        this.recentlyMeetings = recentlyMeetings;
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

    public List<Integer> getMeetingLists() {
        return meetingLists;
    }

    public void setMeetingLists(List<Integer> meetingLists) {
        this.meetingLists = meetingLists;
    }

    public List<Integer> getRecentlyMeetings() {
        return recentlyMeetings;
    }

    public void setRecentlyMeetings(List<Integer> recentlyMeetings) {
        this.recentlyMeetings = recentlyMeetings;
    }
}
