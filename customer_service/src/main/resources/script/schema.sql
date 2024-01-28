CREATE TABLE IF NOT EXISTS `Users` (
    `user_id`       NVARCHAR(20) NOT NULL,
    `user_password` NVARCHAR(20) NOT NULL,
    `user_name`     NVARCHAR(50) NULL,
    `user_is_admin` BOOLEAN      NOT NULL,

    PRIMARY KEY (`user_id`)
);

INSERT INTO `Users` VALUES ( 'admin', '0000', '관리자', true );
INSERT INTO `Users` VALUES ( 'customer1', '12345', '고객1', false );
INSERT INTO `Users` VALUES ( 'customer2', '54321', '고객2', false );

CREATE TABLE IF NOT EXISTS `InquiryTypes` (
    `inquiry_type_id`          INT          NOT NULL,
    `inquiry_type_code`        NVARCHAR(50) NOT NULL,
    `inquiry_type_code_korean` NVARCHAR(50) NOT NULL,

    PRIMARY KEY (`inquiry_type_id`)
);

INSERT INTO `InquiryTypes` VALUES ( 0, 'CUSTOMER_COMPLAINT', '고객 불편' );
INSERT INTO `InquiryTypes` VALUES ( 1, 'IMPROVEMENT_SUGGESTION', '개선점 제안' );
INSERT INTO `InquiryTypes` VALUES ( 2, 'REFUND_AND_EXCHANGE', '환불 및 교환' );
INSERT INTO `InquiryTypes` VALUES ( 3, 'EMPLOYEE_PRAISE', '직원 칭찬' );
INSERT INTO `InquiryTypes` VALUES ( 4, 'ETC', '기타 문의' );

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

INSERT INTO `Inquiries` VALUES (  1, 0, 'customer1', '2020-11-11', '고객 불편 1', '고객1의 불편 1', true  );
INSERT INTO `Inquiries` VALUES (  2, 0, 'customer2', '2020-11-13', '고객 불편 2', '고객2의 불편 1', false );
INSERT INTO `Inquiries` VALUES (  3, 0, 'customer1', '2020-11-17', '고객 불편 3', '고객1의 불편 2', false );
INSERT INTO `Inquiries` VALUES (  4, 0, 'customer2', '2020-12-02', '고객 불편 4', '고객2의 불편 2', false );
INSERT INTO `Inquiries` VALUES (  5, 0, 'customer1', '2020-12-16', '고객 불편 5', '고객1의 불편 3', false );

INSERT INTO `Inquiries` VALUES (  6, 1, 'customer1', '2020-11-01', '개선점 제안 1', '고객1의 개선점 1', true  );
INSERT INTO `Inquiries` VALUES (  7, 1, 'customer2', '2020-11-03', '개선점 제안 2', '고객2의 개선점 1', false );
INSERT INTO `Inquiries` VALUES (  8, 1, 'customer1', '2020-12-17', '개선점 제안 3', '고객1의 개선점 2', false );
INSERT INTO `Inquiries` VALUES (  9, 1, 'customer2', '2021-01-12', '개선점 제안 4', '고객2의 개선점 2', false );
INSERT INTO `Inquiries` VALUES ( 10, 1, 'customer1', '2020-02-16', '개선점 제안 5', '고객1의 개선점 3', false );

INSERT INTO `Inquiries` VALUES ( 11, 2, 'customer1', '2020-11-05', '환불 및 교환 1', '고객1의 환불 및 교환 1', true  );
INSERT INTO `Inquiries` VALUES ( 12, 2, 'customer2', '2020-11-08', '환불 및 교환 2', '고객2의 환불 및 교환 1', false );
INSERT INTO `Inquiries` VALUES ( 13, 2, 'customer1', '2020-12-27', '환불 및 교환 3', '고객1의 환불 및 교환 2', false );
INSERT INTO `Inquiries` VALUES ( 14, 2, 'customer2', '2021-01-24', '환불 및 교환 4', '고객2의 환불 및 교환 2', false );
INSERT INTO `Inquiries` VALUES ( 15, 2, 'customer1', '2020-03-01', '환불 및 교환 5', '고객1의 환불 및 교환 3', false );

INSERT INTO `Inquiries` VALUES ( 16, 3, 'customer1', '2020-10-10', '직원 칭찬 1', '고객1의 직원 칭찬 1', true  );
INSERT INTO `Inquiries` VALUES ( 17, 3, 'customer2', '2020-10-30', '직원 칭찬 2', '고객2의 직원 칭찬 1', false );
INSERT INTO `Inquiries` VALUES ( 18, 3, 'customer1', '2020-11-19', '직원 칭찬 3', '고객1의 직원 칭찬 2', false );
INSERT INTO `Inquiries` VALUES ( 19, 3, 'customer2', '2021-02-18', '직원 칭찬 4', '고객2의 직원 칭찬 2', false );
INSERT INTO `Inquiries` VALUES ( 20, 3, 'customer1', '2020-02-25', '직원 칭찬 5', '고객1의 직원 칭찬 3', false );

INSERT INTO `Inquiries` VALUES ( 21, 4, 'customer1', '2020-09-05', '기타 문의 1', '고객1의 기타 문의 1', true  );
INSERT INTO `Inquiries` VALUES ( 22, 4, 'customer2', '2020-09-08', '기타 문의 2', '고객2의 기타 문의 1', false );
INSERT INTO `Inquiries` VALUES ( 23, 4, 'customer1', '2020-10-27', '기타 문의 3', '고객1의 기타 문의 2', false );
INSERT INTO `Inquiries` VALUES ( 24, 4, 'customer2', '2021-10-24', '기타 문의 4', '고객2의 기타 문의 2', false );
INSERT INTO `Inquiries` VALUES ( 25, 4, 'customer1', '2022-03-01', '기타 문의 5', '고객1의 기타 문의 3', false );

CREATE TABLE IF NOT EXISTS `Answers` (
    `inquiry_id`     BIGINT       NOT NULL,
    `answerer_id`    NVARCHAR(20) NOT NULL,
    `answered_at`    DATETIME     NULL,
    `answer_content` TEXT         NULL,

    PRIMARY KEY (`inquiry_id`),
    FOREIGN KEY (`inquiry_id`) REFERENCES `Inquiries` (`inquiry_id`),
    FOREIGN KEY (`answerer_id`) REFERENCES `Users` (`user_id`)
);

INSERT INTO `Answers` VALUES (  1, 'admin', '2020-11-11', '관리자 답변 SAMPLE 1' );
INSERT INTO `Answers` VALUES (  6, 'admin', '2020-11-01', '관리자 답변 SAMPLE 2' );
INSERT INTO `Answers` VALUES ( 11, 'admin', '2020-11-05', '관리자 답변 SAMPLE 3' );
INSERT INTO `Answers` VALUES ( 16, 'admin', '2020-10-10', '관리자 답변 SAMPLE 4' );
INSERT INTO `Answers` VALUES ( 21, 'admin', '2020-09-05', '관리자 답변 SAMPLE 5' );