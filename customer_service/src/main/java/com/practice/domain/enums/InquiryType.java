package com.practice.domain.enums;

import lombok.Getter;

@Getter
public enum InquiryType {
    CUSTOMER_COMPLAINT("고객 불만"),
    IMPROVEMENT_SUGGESTION("개선점 제안"),
    REFUND_AND_EXCHANGE("환불 및 교환"),
    EMPLOYEE_PRAISE("직원 칭찬"),
    ETC("기타 문의");

    private final String value;

    InquiryType(String value) {
        this.value = value;
    }
}
