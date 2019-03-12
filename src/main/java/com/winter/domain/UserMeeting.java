package com.winter.domain;

import java.util.Date;

public class UserMeeting {
    private Integer id;

    private Integer meetingId;

    private Integer userId;

    private Integer userStatus;

    private Date createTime;

    private Date updateTime;

    public UserMeeting(Integer id, Integer meetingId, Integer userId, Integer userStatus, Date createTime, Date updateTime) {
        this.id = id;
        this.meetingId = meetingId;
        this.userId = userId;
        this.userStatus = userStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public UserMeeting() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
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