package com.winter.domain;

import java.util.Date;

public class Meeting {
    private Integer id;

    private String meetingName;

    private String meetingIntro;

    private Integer roomId;

    private Integer status;

    private Integer masterId;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    private Date updateTime;

    public Meeting(Integer id, String meetingName, String meetingIntro, Integer roomId, Integer status, Integer masterId, Date startTime, Date endTime, Date createTime, Date updateTime) {
        this.id = id;
        this.meetingName = meetingName;
        this.meetingIntro = meetingIntro;
        this.roomId = roomId;
        this.status = status;
        this.masterId = masterId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Meeting() {
        super();
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
        this.meetingName = meetingName == null ? null : meetingName.trim();
    }

    public String getMeetingIntro() {
        return meetingIntro;
    }

    public void setMeetingIntro(String meetingIntro) {
        this.meetingIntro = meetingIntro == null ? null : meetingIntro.trim();
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
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