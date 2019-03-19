package com.winter.controller;

import com.winter.common.Const;
import com.winter.common.ServerResponse;
import com.winter.domain.User;
import com.winter.service.IFileService;
import com.winter.service.IUserService;
import com.winter.util.MD5Util;
import com.winter.util.PropertiesUtil;
import com.winter.util.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

        user.setRole(Const.Role.USER);
        String avatarFileName = fileService.upload(avatarFile,PropertiesUtil.getProperty("upload_path"));
        String avatarUrl = PropertiesUtil.getProperty("image.server.http.prefix") + avatarFileName;

        String faceFileName = fileService.upload(faceFile,PropertiesUtil.getProperty("upload_path"));
        String faceUrl = PropertiesUtil.getProperty("image.server.http.prefix") + faceFileName;

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
    public ServerResponse<User> login(String phone, String password) {
        ServerResponse<User> serverResponse = userService.login(phone,password);
        if (serverResponse.isSuccess()) {
            Integer id = serverResponse.getData().getId();
            String token = TokenUtil.sign(id,phone);
            if (token != null) {
                return ServerResponse.createBySuccess(token,serverResponse.getData());
            }
        }
        return ServerResponse.createByErrorMessage("登录失败");
    }


    /**
     * 获取所有用户信息
     * @return
     */
    @RequestMapping(value = "/getAll.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<User>> getAll() {
        return userService.getAll();
    }


    /**
     * 获取指定用户信息
     * @return
     */
    @RequestMapping(value = "/getOneByPhone.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getOneByPhone(String phone) {
        return userService.getOneByPhone(phone);
    }


    /**
     * 修改用户信息
     * @return
     */
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> update(@RequestParam(value = "id") Integer id, @RequestParam(value = "phone") String phone,
                                        @RequestParam(value = "sex") String sex,
                                       @RequestParam(value = "email") String email, @RequestParam(value = "avatar",required = false) MultipartFile avatarFile,
                                       HttpServletRequest request) {
        String token = request.getHeader("token");
        Integer tokenId = Integer.parseInt(TokenUtil.getInfo(token,"id"));
        if (tokenId.intValue() == id.intValue()) {
            if (avatarFile == null) {
                return userService.updateWithoutAvatar(id,phone,sex,email);
            } else {
                String path = PropertiesUtil.getProperty("upload_path");

                String avatarFileName = fileService.upload(avatarFile,path);
                String avatarUrl = PropertiesUtil.getProperty("image.server.http.prefix") + avatarFileName;
                return userService.update(id,phone,sex,email,avatarUrl);
            }
        }
        return ServerResponse.createByErrorMessage("无权限操作!");
    }

    /**
     * 修改用户密码
     */
    @ResponseBody
    @RequestMapping(value = "/updatePassword.do",method = RequestMethod.POST)
    public ServerResponse<User> updatePassword(Integer userId, String oldPassword, String newPassword,
                                               HttpServletRequest request) {
        String token = request.getHeader("token");
        Integer tokenId = Integer.parseInt(TokenUtil.getInfo(token,"id"));
        if (tokenId.intValue() == userId.intValue()) {
            String pass = userService.getUserPassword(userId);
            if (pass == null || StringUtils.isBlank(pass)) {
                return ServerResponse.createByErrorMessage("未查找到该账号!");
            }
            if (pass.equals(MD5Util.MD5EncodeUtf8(oldPassword))) {
                return userService.updatePassword(userId,MD5Util.MD5EncodeUtf8(newPassword));
            }
            return ServerResponse.createByErrorMessage("旧密码错误!");
        }
        return ServerResponse.createByErrorMessage("无权限操作!");
    }

}

