package com.winter.domain;

import java.util.Date;

public class UserMeeting {
    private Integer id;
    private String meetingName;
    private Integer userId;
    private Date startTime;
    private Date endTime;
    private Integer roomId;
    private Integer masterId;

    private Date createTime;
    private Date updateTime;

    public UserMeeting() {
    }

    public UserMeeting(Integer id, String meetingName, Integer userId, Date startTime, Date endTime, Integer roomId,
                       Integer masterId, Date createTime, Date updateTime) {
        this.id = id;
        this.meetingName = meetingName;
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomId = roomId;
        this.masterId = masterId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
