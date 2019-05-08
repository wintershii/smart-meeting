package com.winter.domain;

import java.util.Date;

public class MeetingFile {
    private Integer id;

    private Integer meetingId;

    private String fileName;

    private String fileUrl;

    private Integer fileSize;

    private Integer upId;

    private String uploader;

    private Date uploadTime;


    public MeetingFile() {
    }

    public MeetingFile(Integer id, Integer meetingId, String fileName, String fileUrl, Integer fileSize, Integer upId, String uploader, Date uploadTime) {
        this.id = id;
        this.meetingId = meetingId;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.fileSize = fileSize;
        this.upId = upId;
        this.uploader = uploader;
        this.uploadTime = uploadTime;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getUpId() {
        return upId;
    }

    public void setUpId(Integer upId) {
        this.upId = upId;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}