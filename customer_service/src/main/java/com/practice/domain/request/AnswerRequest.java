package com.practice.domain.request;

import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
public class AnswerRequest {
    Long id;

    String answerer;

    String answeredAt;

    @Length(min = 1, max = 40000)
    String answer;

}
