package com.practice.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/*
CREATE TABLE IF NOT EXISTS `InquiryTypes` (
    `inquiry_type_id`          INT          NOT NULL,
    `inquiry_type_code`        NVARCHAR(50) NOT NULL,
    `inquiry_type_code_korean` NVARCHAR(50) NOT NULL,

    PRIMARY KEY (`inquiry_type_id`)
);
*/

@Entity
@EqualsAndHashCode
@Getter
@Setter
@Table(name = "InquiryTypes")
public class InquiryTypeEntity {
    @Id
    @Column(name = "inquiry_type_id")
    private Integer id;

    @Column(name = "inquiry_type_code")
    private String code;

    @Column(name = "inquiry_type_code_korean")
    private String koreanCode;
}
