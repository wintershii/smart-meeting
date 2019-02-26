package com.winter.vo;

import java.util.Map;

public class MeetingVo {
    private Integer meetingId;
    private String meetingName;
    private String startTime;
    private String endTime;
    private Map<Integer,Integer> memberStatus;
    private Integer roomId;
    private Integer masterId;

    public MeetingVo() {
    }

    public MeetingVo(Integer meetingId, String meetingName, String startTime, String endTime,
                     Map<Integer, Integer> memberStatus, Integer roomId, Integer masterId) {
        this.meetingId = meetingId;
        this.meetingName = meetingName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.memberStatus = memberStatus;
        this.roomId = roomId;
        this.masterId = masterId;
    }

    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Map<Integer, Integer> getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(Map<Integer, Integer> memberStatus) {
        this.memberStatus = memberStatus;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }
}
