package com.winter.service;

import com.winter.common.ServerResponse;
import com.winter.domain.User;

import java.util.List;

public interface IUserService {
    ServerResponse<String> register(User user);
    ServerResponse<User> login(String username, String password);
    ServerResponse<List<User>> getAll();
}
