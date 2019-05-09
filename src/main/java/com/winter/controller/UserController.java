package com.winter.controller;

import com.winter.common.Const;
import com.winter.common.ResponseCode;
import com.winter.common.ServerResponse;
import com.winter.domain.MeetingFile;
import com.winter.domain.User;
import com.winter.service.IFileService;
import com.winter.service.IUserService;
import com.winter.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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

    private RedisUtil redisUtil;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setFileService(IFileService fileService) {
        this.fileService = fileService;
    }
    @Autowired
    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
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
        if(avatarFile == null) {
            user.setAvatarUrl(PropertiesUtil.getProperty("default_avatar_url"));
        } else {
            String avatarFileName = fileService.upload(avatarFile,PropertiesUtil.getProperty("upload_path"));
            String avatarUrl = PropertiesUtil.getProperty("image.server.http.prefix") + avatarFileName;
            user.setAvatarUrl(avatarUrl);
        }

        if (faceFile == null) {
            return ServerResponse.createByErrorMessage("缺少人脸图片参数!");
        }

        String faceFileName = fileService.upload(faceFile,PropertiesUtil.getProperty("upload_path"));
        String faceUrl = PropertiesUtil.getProperty("image.server.http.prefix") + faceFileName;


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
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(password)) {
            return ServerResponse.createByErrorMessage("参数无效!");
        }

        ServerResponse<User> serverResponse = userService.login(phone,password);
        if (serverResponse.isSuccess()) {
            Integer id = serverResponse.getData().getId();
            String token = TokenUtil.sign(id,phone);
            if (token != null) {
                redisUtil.setex(PropertiesUtil.getProperty("redis_prefix")+id,3600*24*15,token);
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
        if (StringUtils.isBlank(phone)) {
            return ServerResponse.createByErrorMessage("参数无效!");
        }
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
        if (id == null) {
            return ServerResponse.createByErrorMessage("参数无效!");
        }
        if (StringUtils.isAnyBlank(phone,sex,email)) {
            return ServerResponse.createByErrorMessage("参数无效!");
        }

        String token = request.getHeader("token");
        Integer tokenId = Integer.parseInt(TokenUtil.getInfo(token,"id"));
        if (tokenId.intValue() == id.intValue()) {

            String realPhone = userService.getPhoneById(id);
            if(!realPhone.equals(phone)) {
                if (!userService.checkValid(phone,"phone").isSuccess()) {
                    return ServerResponse.createByErrorMessage("该手机号已绑定其他账号!");
                }
            }

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
        if (userId == null) {
            return ServerResponse.createByErrorMessage("参数无效!");
        }
        if (StringUtils.isAnyBlank(oldPassword,newPassword)) {
            return ServerResponse.createByErrorMessage("参数无效!");
        }
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

    /**
     * 忘记密码
     * @param code
     * @param phoneNumber
     * @param newPassword
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/forgetPassword.do",method = RequestMethod.POST)
    public ServerResponse forgetPassword(String code,String phoneNumber,String newPassword) {
        if (StringUtils.isNoneBlank(code,phoneNumber,newPassword)) {
            if (PhoneUtil.judgeCodeIsTrue(code,phoneNumber)) {
                return userService.forgetPassword(phoneNumber,MD5Util.MD5EncodeUtf8(newPassword));
            }
            return ServerResponse.createByErrorMessage("验证失败");
        }
        return ServerResponse.createByErrorMessage("参数无效!");
    }

    /**
     * 申请请假
     * @param userId
     * @param meetingId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/applyLeave.do",method = RequestMethod.POST)
    public ServerResponse applyLeave(Integer userId, Integer meetingId, HttpServletRequest request) {
        if (userId == null || meetingId == null) {
            return ServerResponse.createByErrorMessage("参数错误!");
        }
        String token = request.getHeader("token");
        Integer tokenId = Integer.parseInt(TokenUtil.getInfo(token,"id"));
        if (tokenId.intValue() == userId.intValue()) {
            return userService.applyMeeting(userId,meetingId);
        }
        return ServerResponse.createByErrorMessage("无权限操作!");
    }

    /**
     * 根据用户id获取之前所上传的文件
     * @param userId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getMyFiles.do",method = RequestMethod.GET)
    public ServerResponse<List<MeetingFile>> getUserMeetingFiles(Integer userId, HttpServletRequest request) {
        if (userId == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        String token = request.getHeader("token");
        Integer tokenId = Integer.parseInt(TokenUtil.getInfo(token,"id"));
        if (tokenId.intValue() == userId.intValue()) {
            return fileService.getUserMeetingFiles(userId);
        }
        return ServerResponse.createByErrorMessage("无权限操作!");
    }

    /**
     * token无效后作出响应
     * @return
     */
    @ResponseBody
    @RequestMapping("/tokenExpired.do")
    public ServerResponse tokenExpired() {
        return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"token过期!");
    }

    /**
     * token延期响应  响应码--100
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/offerToken.do")
    public ServerResponse offerToken(HttpServletRequest request) {
        String id = (String) request.getAttribute("id");
        String phone = (String) request.getAttribute("phone");
        String token = TokenUtil.sign(Integer.parseInt(id),phone);
        String key = PropertiesUtil.getProperty("redis_prefix") + id;
        redisUtil.set(key,token);
        return ServerResponse.createByErrorCodeMessage(ResponseCode.FRESH_TOKEN.getCode(),token);
    }



}

