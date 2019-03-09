package com.winter.service.impl;

import com.winter.service.IFileService;
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
}
