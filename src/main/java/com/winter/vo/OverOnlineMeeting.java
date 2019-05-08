package com.winter.vo;

import java.util.List;

public class OverOnlineMeeting {
    private Integer id;

    private String liveName;

    private Integer status;

    private Integer onlineNum;

    private List<UserAvatarInfo> meetingMembers;

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

    public List<UserAvatarInfo> getMeetingMembers() {
        return meetingMembers;
    }

    public void setMeetingMembers(List<UserAvatarInfo> meetingMembers) {
        this.meetingMembers = meetingMembers;
    }
}
