package com.practice.repository;

import com.practice.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    boolean existsByIdAndPwdAndIsAdmin(String id, String pwd, Integer isAdmin);
}
