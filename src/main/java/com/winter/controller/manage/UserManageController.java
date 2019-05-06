package com.winter.controller.manage;

import com.winter.common.ServerResponse;
import com.winter.domain.User;
import com.winter.service.IUserService;
import com.winter.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userManage")
public class UserManageController {

    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 删除用户信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/deleteUser",method = RequestMethod.DELETE)
    public ServerResponse deleteUser(Integer userId) {
        if (userId == null) {
            return ServerResponse.createByErrorMessage("参数错误!");
        }
        return userService.deleteUser(userId);
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    public ServerResponse updateUserInfo(User user) {
        String realPhone = userService.getPhoneById(user.getId());
        if(!realPhone.equals(user.getPhone())) {
            if (!userService.checkValid(user.getPhone(),"phone").isSuccess()) {
                return ServerResponse.createByErrorMessage("该手机号已绑定其他账号!");
            }
        }
        if (user.getPassword() != null) {
            user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        } else {
            user.setPassword(null);
        }
        return userService.updateUserManage(user);
    }
}
