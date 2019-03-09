package com.winter.vo;

import java.util.Map;

public class MeetingVo {
    private Integer meetingId;
    private String meetingName;
    private String meetingIntro;
    private Integer peopleNum;
    private String startTime;
    private String endTime;
    private Integer status;
    private Integer userStatus;
    private Map<Integer,Integer> memberStatus;
    private Integer roomId;
    private String roomName;
    private Integer masterId;


    public MeetingVo() {
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

    public String getMeetingIntro() {
        return meetingIntro;
    }

    public void setMeetingIntro(String meetingIntro) {
        this.meetingIntro = meetingIntro;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
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

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }
}
