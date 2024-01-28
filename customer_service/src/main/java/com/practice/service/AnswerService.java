package com.practice.service;

import com.practice.domain.entity.AnswerEntity;
import com.practice.domain.entity.InquiryEntity;
import com.practice.domain.entity.UserEntity;
import java.time.LocalDate;

public interface AnswerService {
    AnswerEntity getAnswer(Long inquiryId);

    void answer(Long id, String answerer, LocalDate answeredAt, String content);
}
