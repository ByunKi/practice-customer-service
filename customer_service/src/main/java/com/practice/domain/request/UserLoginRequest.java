package com.practice.domain.request;

import lombok.Value;

@Value
public class UserLoginRequest {
    String id;

    String pwd;

    Integer isAdmin;
}
