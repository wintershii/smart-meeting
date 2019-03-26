package com.winter.vo;

import java.util.List;
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
    private List<UserStatus> memberStatus;
    private Integer roomId;
    private String roomName;
    private Integer masterId;
    private String masterName;


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

    public List<UserStatus> getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(List<UserStatus> memberStatus) {
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

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    @Override
    public String toString() {
        return "MeetingVo{" +
                "meetingId=" + meetingId +
                ", meetingName='" + meetingName + '\'' +
                ", meetingIntro='" + meetingIntro + '\'' +
                ", peopleNum=" + peopleNum +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", status=" + status +
                ", userStatus=" + userStatus +
                ", memberStatus=" + memberStatus +
                ", roomId=" + roomId +
                ", roomName='" + roomName + '\'' +
                ", masterId=" + masterId +
                '}';
    }
}
