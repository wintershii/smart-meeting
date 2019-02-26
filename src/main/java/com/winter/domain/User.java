package com.winter.domain;

import java.util.Date;

public class User {
    private Integer id;
    private String name;
    private String password;
    private String sex;
    private String phoneNumber;
    private String email;
    private String faceData;
    private String avatarUrl;
    private String faceUrl;

    private Date createTime;
    private Date updateTime;

    public User() {
    }

    public User(Integer id, String name, String password, String sex, String phoneNumber, String email,
                String faceData, String avatarUrl, String faceUrl, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.faceData = faceData;
        this.avatarUrl = avatarUrl;
        this.faceUrl = faceUrl;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFaceData() {
        return faceData;
    }

    public void setFaceData(String faceData) {
        this.faceData = faceData;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
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
