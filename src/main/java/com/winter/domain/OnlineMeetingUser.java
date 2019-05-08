package com.winter.domain;

import java.io.Serializable;

public class OnlineMeetingUser implements Serializable {
    private Integer id;

    private Integer liveId;

    private Integer memberId;

    public OnlineMeetingUser(Integer id, Integer liveId, Integer memberId) {
        this.id = id;
        this.liveId = liveId;
        this.memberId = memberId;
    }

    public OnlineMeetingUser() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLiveId() {
        return liveId;
    }

    public void setLiveId(Integer liveId) {
        this.liveId = liveId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "OnlineMeetingUser{" +
                "id=" + id +
                ", liveId=" + liveId +
                ", memberId=" + memberId +
                '}';
    }
}