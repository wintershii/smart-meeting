package com.winter.dao;

import com.winter.domain.User;
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

    int updateInfo(@Param("id") Integer id, @Param("phone") String phone, @Param("password") String password,
                   @Param("sex") String sex, @Param("email") String email, @Param("avatarUrl") String avatarUrl);

    String getNameById(Integer userId);

}