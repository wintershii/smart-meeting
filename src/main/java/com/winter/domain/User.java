package com.winter.domain;

import java.util.Date;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String sex;
    private String phone;
    private String email;
    private String faceData;
    private String avatarUrl;
    private String faceUrl;

    private Date createTime;
    private Date updateTime;


    public User(Integer id, String username, String password, String sex, String phone, String email, String faceData, String avatarUrl, String faceUrl, Date createTime, Date updateTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.phone = phone;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
