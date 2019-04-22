package com.winter.vo;

import java.util.Date;
import java.util.List;

public class VoteVo {
    private Integer voteId;
    private String topic;
    private Integer selectWay;//0-单选 1-多选
    private UserAvatarInfo userInfo;
    private Date createTime;
    private Date endTime;
    private List<VoteOption> optionList;
    private List<Integer> userSelectList;

    public Integer getVoteId() {
        return voteId;
    }

    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getSelectWay() {
        return selectWay;
    }

    public void setSelectWay(Integer selectWay) {
        this.selectWay = selectWay;
    }

    public UserAvatarInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserAvatarInfo userInfo) {
        this.userInfo = userInfo;
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

    public List<VoteOption> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<VoteOption> optionList) {
        this.optionList = optionList;
    }

    public List<Integer> getUserSelectList() {
        return userSelectList;
    }

    public void setUserSelectList(List<Integer> userSelectList) {
        this.userSelectList = userSelectList;
    }
}
