package com.practice.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/*
CREATE TABLE IF NOT EXISTS `Users` (
    `user_id`       NVARCHAR(20) NOT NULL,
    `user_password` NVARCHAR(20) NOT NULL,
    `user_name`     NVARCHAR(50) NULL,
    `user_is_admin` BOOLEAN      NOT NULL,

    PRIMARY KEY (`user_id`)
);
*/

@Entity
@EqualsAndHashCode
@Getter
@Setter
@Table(name = "Users")
public class UserEntity {
    @Id
    @Column(name = "user_id")
    private String id;

    @Column(name = "user_password")
    private String pwd;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_is_admin")
    private Integer isAdmin;
}
