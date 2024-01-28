package com.practice.service.impl;

import com.practice.domain.entity.AnswerEntity;
import com.practice.domain.entity.InquiryEntity;
import com.practice.domain.entity.UserEntity;
import com.practice.exception.InquiryNotFoundException;
import com.practice.exception.UserNotFoundException;
import com.practice.repository.AnswerRepository;
import com.practice.repository.InquiryRepository;
import com.practice.repository.UserRepository;
import com.practice.service.AnswerService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;

    private final InquiryRepository inquiryRepository;

    private final UserRepository userRepository;

    @Override
    public AnswerEntity getAnswer(Long inquiryId) {
        return answerRepository.findById(inquiryId)
                .orElse(null);
    }

    @Override
    public void answer(Long id, String answerer, LocalDate answeredAt, String content) {
        InquiryEntity inquiry = inquiryRepository.findById(id)
                .orElseThrow(InquiryNotFoundException::new);
        inquiry.setIsAnswered(1);

        UserEntity author = userRepository.findById(answerer)
                .orElseThrow(UserNotFoundException::new);

        AnswerEntity target = new AnswerEntity();
        target.setAnswerId(id);
        target.setInquiry(inquiry);
        target.setAnswerer(author);
        target.setAnsweredAt(answeredAt);
        target.setContent(content);

        answerRepository.saveAndFlush(target);
        inquiryRepository.saveAndFlush(inquiry);
    }
}
