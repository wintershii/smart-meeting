package com.winter.service.impl;

import com.google.common.collect.Lists;
import com.winter.service.IFileService;
import com.winter.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileServiceImpl implements IFileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    public String upload(MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();

        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        logger.info("开始上传文件,上传的文件名:{},上传的路径:{},新文件名:{}",fileName,fileExtensionName,uploadFileName);

        File fileDir = new File(path);
        if ( !fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path,uploadFileName);
        try {
            file.transferTo(targetFile);

            //将targetFile上传到FTP服务器上
            boolean isSuccess = FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            if (isSuccess) {
                //上传完后，删除upload下面的文件
                targetFile.delete();
            }

        } catch (IOException e) {
            logger.error("上传文件异常");
            return null;
        }

        return targetFile.getName();
    }
}
