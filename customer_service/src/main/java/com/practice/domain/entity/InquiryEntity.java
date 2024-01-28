package com.practice.domain.entity;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
CREATE TABLE IF NOT EXISTS `Inquiries` (
    `inquiry_id`          BIGINT       AUTO_INCREMENT,
    `type_id`             INT          NOT NULL,
    `inquirer_id`         NVARCHAR(20) NOT NULL,
    `inquired_at`         DATETIME     NULL,
    `inquiry_title`       NVARCHAR(20) NOT NULL,
    `inquiry_content`     TEXT         NULL,
    `inquiry_is_answered` BOOLEAN      NULL,

    PRIMARY KEY (`inquiry_id`),
    FOREIGN KEY (`inquirer_id`) REFERENCES `Users` (`user_id`),
    FOREIGN KEY (`type_id`) REFERENCES `InquiryTypes` (`inquiry_type_id`)
);
*/

@Entity
@EqualsAndHashCode
@Getter
@Setter
@Table(name = "Inquiries")
public class InquiryEntity {
    @Id
    @Column(name = "inquiry_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "type_id")
    private InquiryTypeEntity type;

    @OneToOne
    @JoinColumn(name = "inquirer_id")
    private UserEntity inquirer;

    @Column(name = "inquired_at")
    private LocalDate inquiredAt;

    @Column(name = "inquiry_title")
    private String title;

    @Column(name = "inquiry_content")
    private String content;

    @Column(name = "inquiry_is_answered")
    private Integer isAnswered;

}
