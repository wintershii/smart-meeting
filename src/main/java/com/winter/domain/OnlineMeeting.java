package com.winter.domain;

import java.util.Date;

public class OnlineMeeting {
    private Integer id;

    private String liveName;

    private String livePwd;

    private Integer createId;

    private Integer onlineNum;

    private Integer status;

    private Date startTime;

    private Date endTime;

    public OnlineMeeting(Integer id, String liveName, String livePwd, Integer createId, Integer onlineNum, Integer status, Date startTime, Date endTime) {
        this.id = id;
        this.liveName = liveName;
        this.livePwd = livePwd;
        this.createId = createId;
        this.onlineNum = onlineNum;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public OnlineMeeting() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLiveName() {
        return liveName;
    }

    public void setLiveName(String liveName) {
        this.liveName = liveName == null ? null : liveName.trim();
    }

    public String getLivePwd() {
        return livePwd;
    }

    public void setLivePwd(String livePwd) {
        this.livePwd = livePwd == null ? null : livePwd.trim();
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Integer getOnlineNum() {
        return onlineNum;
    }

    public void setOnlineNum(Integer onlineNum) {
        this.onlineNum = onlineNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OnlineMeeting{" +
                "id=" + id +
                ", liveName='" + liveName + '\'' +
                ", livePwd='" + livePwd + '\'' +
                ", createId=" + createId +
                ", onlineNum=" + onlineNum +
                ", status=" + status +
                '}';
    }
}