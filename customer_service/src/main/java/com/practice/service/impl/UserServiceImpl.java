package com.practice.service.impl;

import com.practice.domain.entity.UserEntity;
import com.practice.domain.request.UserLoginRequest;
import com.practice.exception.UserNotFoundException;
import com.practice.repository.UserRepository;
import com.practice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public boolean matches(UserLoginRequest user) {
        return userRepository.existsByIdAndPwdAndIsAdmin(user.getId(), user.getPwd(), user.getIsAdmin());
    }

    @Override
    public UserEntity getUser(String id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }
}
