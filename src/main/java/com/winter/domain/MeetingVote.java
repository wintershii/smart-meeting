package com.winter.domain;

import java.util.Date;

public class MeetingVote {
    private Integer id;

    private Integer meetingId;

    private Integer publisherId;

    private String topic;

    private Integer selectWay; // 0-单选 1-多选

    private Integer remindTime;

    private Date createTime;

    private Date endTime;

    public MeetingVote(Integer id, Integer meetingId, Integer publisherId, String topic, Integer selectWay, Integer remindTime, Date createTime, Date endTime) {
        this.id = id;
        this.meetingId = meetingId;
        this.publisherId = publisherId;
        this.topic = topic;
        this.selectWay = selectWay;
        this.remindTime = remindTime;
        this.createTime = createTime;
        this.endTime = endTime;
    }

    public MeetingVote() {
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

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic == null ? null : topic.trim();
    }

    public Integer getSelectWay() {
        return selectWay;
    }

    public void setSelectWay(Integer selectWay) {
        this.selectWay = selectWay;
    }

    public Integer getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(Integer remindTime) {
        this.remindTime = remindTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}