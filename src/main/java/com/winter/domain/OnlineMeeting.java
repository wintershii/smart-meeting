package com.winter.domain;

public class OnlineMeeting {
    private Integer id;

    private String liveName;

    private String livePwd;

    private Integer createId;

    private Integer onlineNum;

    private Integer status;

    public OnlineMeeting(Integer id, String liveName, String livePwd, Integer createId, Integer onlineNum, Integer status) {
        this.id = id;
        this.liveName = liveName;
        this.livePwd = livePwd;
        this.createId = createId;
        this.onlineNum = onlineNum;
        this.status = status;
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