package com.winter.domain;

import java.util.Date;

public class User {
    private Integer id;

    private String username;

    private String password;

    private String sex;

    private Integer role;

    private String phone;

    private String email;

    private String avatarUrl;

    private String faceUrl;

    private Date createTime;

    private Date updateTime;

    private String faceData;

    public User(Integer id, String username, String password, String sex, Integer role, String phone, String email, String avatarUrl, String faceUrl, Date createTime, Date updateTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.role = role;
        this.phone = phone;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.faceUrl = faceUrl;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public User(Integer id, String username, String password, String sex, Integer role, String phone, String email, String avatarUrl, String faceUrl, Date createTime, Date updateTime, String faceData) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.role = role;
        this.phone = phone;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.faceUrl = faceUrl;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.faceData = faceData;
    }

    public User() {
        super();
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
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl == null ? null : faceUrl.trim();
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

    public String getFaceData() {
        return faceData;
    }

    public void setFaceData(String faceData) {
        this.faceData = faceData == null ? null : faceData.trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", role=" + role +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", faceUrl='" + faceUrl + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", faceData='" + faceData + '\'' +
                '}';
    }
}