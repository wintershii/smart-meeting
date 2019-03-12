package com.winter.vo;

import com.winter.domain.Meeting;

import java.util.List;

public class RoomVo {
    private Integer Id;
    private String roomNumber;
    private Integer content;
    private String machineNumber;
    private Integer status;
    private List<Meeting> meetingLists;
    private List<Meeting> recentlyMeetings;

    public RoomVo() {
    }

    public Integer getContent() {
        return content;
    }

    public void setContent(Integer content) {
        this.content = content;
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

    public List<Meeting> getMeetingLists() {
        return meetingLists;
    }

    public void setMeetingLists(List<Meeting> meetingLists) {
        this.meetingLists = meetingLists;
    }

    public List<Meeting> getRecentlyMeetings() {
        return recentlyMeetings;
    }

    public void setRecentlyMeetings(List<Meeting> recentlyMeetings) {
        this.recentlyMeetings = recentlyMeetings;
    }
}
