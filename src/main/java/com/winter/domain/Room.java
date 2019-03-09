package com.winter.domain;

import java.util.Date;

public class Room {
    private Integer id;

    private String roomNumber;

    private Integer content;

    private String machineNumber;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public Room(Integer id, String roomNumber, Integer content, String machineNumber, Integer status, Date createTime, Date updateTime) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.content = content;
        this.machineNumber = machineNumber;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Room() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber == null ? null : roomNumber.trim();
    }

    public Integer getContent() {
        return content;
    }

    public void setContent(Integer content) {
        this.content = content;
    }

    public String getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber == null ? null : machineNumber.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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