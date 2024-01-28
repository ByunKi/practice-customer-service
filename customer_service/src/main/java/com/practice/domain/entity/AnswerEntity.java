package com.practice.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/*
CREATE TABLE IF NOT EXISTS `Answers` (
    `inquiry_id`     BIGINT       NOT NULL,
    `answerer_id`    NVARCHAR(20) NOT NULL,
    `answered_at`    DATETIME     NULL,
    `answer_content` TEXT         NULL,

    PRIMARY KEY (`inquiry_id`),
    FOREIGN KEY (`inquiry_id`) REFERENCES `Inquiries` (`inquiry_id`),
    FOREIGN KEY (`answerer_id`) REFERENCES `Users` (`user_id`)
);
*/

@Entity
@EqualsAndHashCode
@Getter
@Setter
@Table(name = "Answers")
@RequiredArgsConstructor
public class AnswerEntity {
    @Id
    private Long answerId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "inquiry_id")
    private InquiryEntity inquiry;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answerer_id")
    private UserEntity answerer;

    @Column(name = "answered_at")
    private LocalDate answeredAt;

    @Column(name = "answer_content")
    private String content;
}
