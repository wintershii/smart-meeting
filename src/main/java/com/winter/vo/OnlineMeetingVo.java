package com.winter.vo;

import java.util.Date;

public class OnlineMeetingVo {
    private Integer id;

    private String liveName;

    private String livePwd;

    private UserAvatarInfo userAvatarInfo;

    private Integer onlineNum;

    private Integer status;


    private Date startTime;

    private Date endTime;

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
        this.liveName = liveName;
    }

    public String getLivePwd() {
        return livePwd;
    }

    public void setLivePwd(String livePwd) {
        this.livePwd = livePwd;
    }

    public UserAvatarInfo getUserAvatarInfo() {
        return userAvatarInfo;
    }

    public void setUserAvatarInfo(UserAvatarInfo userAvatarInfo) {
        this.userAvatarInfo = userAvatarInfo;
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
}
