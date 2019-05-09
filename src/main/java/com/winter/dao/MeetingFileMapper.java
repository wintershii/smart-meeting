package com.winter.dao;

import com.winter.domain.MeetingFile;

import java.util.List;

public interface MeetingFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MeetingFile record);

    int insertSelective(MeetingFile record);

    MeetingFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MeetingFile record);

    int updateByPrimaryKey(MeetingFile record);

    List<MeetingFile> getMeetingFiles(Integer meetingId);

    int deleteByMeetingId(Integer meetingId);

    List<MeetingFile> getUserFiles(Integer userId);
}