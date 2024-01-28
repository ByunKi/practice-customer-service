package com.practice.service;

import com.practice.domain.entity.UserEntity;
import com.practice.domain.request.UserLoginRequest;

public interface UserService {
    boolean matches(UserLoginRequest user);

    UserEntity getUser(String id);
}
