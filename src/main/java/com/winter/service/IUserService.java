package com.winter.service;

import com.winter.common.ServerResponse;
import com.winter.domain.User;

import java.util.List;

public interface IUserService {
    ServerResponse<String> register(User user);
    ServerResponse<User> login(String phone, String password);
    ServerResponse<List<User>> getAll();
    ServerResponse<User> getOneByPhone(String phone);

    ServerResponse update(Integer id, String phone, String password, String sex, String email, String avatarUrl);
}
