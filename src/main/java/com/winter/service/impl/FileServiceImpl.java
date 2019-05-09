package com.winter.service.impl;

import com.winter.common.ServerResponse;
import com.winter.dao.MeetingFileMapper;
import com.winter.dao.UserMapper;
import com.winter.domain.MeetingFile;
import com.winter.service.IFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements IFileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    private MeetingFileMapper meetingFileMapper;

    private UserMapper userMapper;

    @Autowired
    public void setMeetingFileMapper(MeetingFileMapper meetingFileMapper) {
        this.meetingFileMapper = meetingFileMapper;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public String upload(MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();

        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        logger.info("开始上传文件,上传的文件名:{},上传的路径:{},新文件名:{}",fileName,path,uploadFileName);

        File fileDir = new File(path);
        if ( !fileDir.exists()) {
            boolean flag = fileDir.mkdirs();
            if ( !flag) {
                System.out.println("创建文件夹失败");
            } else {
                System.out.println("新建文件夹");
            }
        }
        File targetFile = new File(path,uploadFileName);

        try {
            targetFile.createNewFile();
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return targetFile.getName();

    }


    @Override
    public ServerResponse uploadMeetingFile(MeetingFile meetingFile, Integer userId) {
        meetingFile.setUploader(userMapper.getNameById(userId));
        meetingFile.setUpId(userId);
        int resultCount = meetingFileMapper.insert(meetingFile);
        if (resultCount > 0) {
            return ServerResponse.createBySuccess("上传文件成功");
        }
        return ServerResponse.createByErrorMessage("上传文件失败");
    }

    @Override
    public ServerResponse<List<MeetingFile>> getMeetingFiles(Integer meetingId) {
        List<MeetingFile> list = meetingFileMapper.getMeetingFiles(meetingId);
        if (list == null) {
            return ServerResponse.createByErrorMessage("获取会议文件失败");
        }
        return ServerResponse.createBySuccess(list);
    }

    @Override
    public ServerResponse<List<MeetingFile>> getUserMeetingFiles(Integer userId) {
        List<MeetingFile> fileList = meetingFileMapper.getUserFiles(userId);
        if (fileList == null) {
            return ServerResponse.createByErrorMessage("查找失败");
        }
        return ServerResponse.createBySuccess(fileList);
    }
}
