package com.winter.dao;

import com.winter.domain.User;
import com.winter.vo.UserAvatarInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);

    int checkPhone(String phone);

    int checkEmail(String email);

    User selectLogin(@Param("phone") String phone, @Param("password") String password);

    List<User> getAll();

    User selectByPhone(String phone);

    int updateInfo(@Param("id") Integer id, @Param("phone") String phone,
                   @Param("sex") String sex, @Param("email") String email, @Param("avatarUrl") String avatarUrl);

    int updateInfoWithoutAvatar(@Param("id") Integer id, @Param("phone") String phone,
                   @Param("sex") String sex, @Param("email") String email);

    String getNameById(Integer userId);

    String getUserPassword(Integer userId);

    int updatePassword(@Param("userId") Integer userId, @Param("newPassword") String newPassword);

    int forgetPassword(@Param("phoneNumber") String phoneNumber, @Param("newPassword") String newPassword);

    String getPhoneById(Integer id);

    UserAvatarInfo getUserAvatarInfo(Integer userId);

    int deleteUser(Integer userId);
}