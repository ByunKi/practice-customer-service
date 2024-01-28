package com.practice.domain.request;

import com.practice.domain.enums.InquiryType;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
public class InquiryRegisterRequest {
    @Length(min = 2, max = 200)
    String title;

    Integer type;

    String inquirer;

    @Length(min = 1, max = 40000)
    String content;
}
