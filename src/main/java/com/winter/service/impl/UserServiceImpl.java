package com.winter.service.impl;

import com.winter.common.Const;
import com.winter.common.ServerResponse;
import com.winter.dao.UserMapper;
import com.winter.domain.User;
import com.winter.service.IUserService;
import com.winter.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 注册新用户
     * @param user
     * @return
     */
    public ServerResponse<String> register(User user) {

        ServerResponse validResponse = this.checkValid(user.getPhone(), Const.PHONE);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }

        validResponse = this.checkValid(user.getEmail(),Const.EMAIL);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }

        //MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));

        int resultCount = userMapper.insert(user);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }


    /**
     * 用户登录
     * @param phone
     * @param password
     * @return
     */
    public ServerResponse<User> login(String phone, String password) {
        int resultCount = userMapper.checkPhone(phone);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("该用户不存在");
        }

        String md5pwd = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(phone,md5pwd);
        if (user == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }

        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登陆成功",user);
    }


    /**
     * 获取所有用户信息
     * @return
     */
    public ServerResponse<List<User>> getAll() {
        List<User> list =  userMapper.getAll();
        for(User user : list) {
            user.setPassword("");
        }
        return ServerResponse.createBySuccess(list);
    }


    /**
     * 检查用户名或邮箱是否可用
     * @param str
     * @param type
     * @return
     */
    public ServerResponse<String> checkValid(String str, String type) {
        if (StringUtils.isNoneBlank(type)) {
            //开始校验
            if (Const.PHONE.equals(type)) {
                int resultCount = userMapper.checkPhone(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("该手机号已注册");
                }
            }

            if (Const.EMAIL.equals(type)) {
                int resultCount = userMapper.checkEmail(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("email已存在");
                }
            }
        } else {
            return ServerResponse.createByErrorMessage("参数错误");
        }

        return ServerResponse.createBySuccess("校验成功");
    }

    /**
     * 通过手机号获取用户信息
     * @param phone
     * @return
     */
    public ServerResponse<User> getOneByPhone(String phone) {
        User user = userMapper.selectByPhone(phone);
        if (user != null) {
            user.setPassword("");
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户不存在!");
    }

    /**
     * 修改用户信息
     * @param id
     * @param phone
     * @param sex
     * @param email
     * @param avatarUrl
     * @return
     */
    @Override
    public ServerResponse update(Integer id, String phone, String password, String sex, String email, String avatarUrl) {
        int resultCount = userMapper.updateInfo(id,phone,password,sex,email,avatarUrl);
        if (resultCount > 0) {
            return ServerResponse.createBySuccessMessage("修改信息成功");
        }
        return ServerResponse.createByErrorMessage("修改信息失败");
    }


    @Override
    public ServerResponse updateWithoutAvatar(Integer id, String phone, String password, String sex, String email) {
        int resultCount = userMapper.updateInfoWithoutAvatar(id,phone,password,sex,email);
        if (resultCount > 0) {
            return ServerResponse.createBySuccessMessage("修改信息成功");
        }
        return ServerResponse.createByErrorMessage("修改信息失败");
    }

}
