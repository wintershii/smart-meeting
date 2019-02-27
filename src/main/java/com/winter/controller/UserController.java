package com.winter.controller;

import com.winter.common.ServerResponse;
import com.winter.domain.User;
import com.winter.service.IFileService;
import com.winter.service.IUserService;
import com.winter.util.PropertiesUtil;
import com.winter.util.TokenUtil;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private IUserService userService;
    private IFileService fileService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setFileService(IFileService fileService) {
        this.fileService = fileService;
    }

    /**
     * 用户注册接口
     * @param user
     * @param request
     * @param avatarFile
     * @param faceFile
     * @return
     */
    @RequestMapping(value = "/register.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(User user, HttpServletRequest request, @RequestParam(value = "avatar", required = false)
            MultipartFile avatarFile, @RequestParam(value = "face", required = false) MultipartFile faceFile) {

        String path = request.getSession().getServletContext().getRealPath("upload");

        String avatarFileName = fileService.upload(avatarFile,path);
        String avatarUrl = PropertiesUtil.getProperty("ftp.server.http.prefix") + avatarFileName;

        String faceFileName = fileService.upload(faceFile,path);
        String faceUrl = PropertiesUtil.getProperty("ftp.server.http.prefix") + faceFileName;

        user.setAvatarUrl(avatarUrl);
        user.setFaceUrl(faceUrl);

        return userService.register(user);
    }

    /**
     * 用户登录
     * @return
     */
    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpServletResponse response) {
        ServerResponse<User> serverResponse = userService.login(username,password);
        if (serverResponse.isSuccess()) {
            String token = TokenUtil.sign(username);
            if (token != null) {
                response.addHeader("token",token);
                return serverResponse;
            }
        }
        return ServerResponse.createByErrorMessage("登录失败");
    }


    @RequestMapping(value = "/getAll.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<User>> getAll() {
        return userService.getAll();
    }

}

