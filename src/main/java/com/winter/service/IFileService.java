package com.winter.service;

import com.winter.common.ServerResponse;
import com.winter.domain.MeetingFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFileService {
    String upload(MultipartFile file, String path);

    ServerResponse uploadMeetingFile(MeetingFile meetingFile, Integer userId);

    ServerResponse<List<MeetingFile>> getMeetingFiles(Integer meetingId);

    ServerResponse<List<MeetingFile>> getUserMeetingFiles(Integer userId);
}
