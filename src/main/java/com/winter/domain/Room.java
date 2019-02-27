package com.winter.domain;

import java.util.Date;

public class Room {
    private Integer Id;
    private String roomNumber;
    private String machineNumber;


    private Date createTime;
    private Date updateTime;


    public Room(Integer id, String roomNumber, String machineNumber, Date createTime, Date updateTime) {
        Id = id;
        this.roomNumber = roomNumber;
        this.machineNumber = machineNumber;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
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
