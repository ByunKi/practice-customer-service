package com.practice.service.impl;

import com.practice.domain.entity.InquiryEntity;
import com.practice.domain.entity.InquiryTypeEntity;
import com.practice.exception.InquiryTypeNotFoundException;
import com.practice.exception.UserNotFoundException;
import com.practice.exception.InquiryNotFoundException;
import com.practice.repository.InquiryRepository;
import com.practice.repository.InquiryTypeRepository;
import com.practice.repository.UserRepository;
import com.practice.service.InquiryService;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {
    private final InquiryRepository inquiryRepository;

    private final InquiryTypeRepository inquiryTypeRepository;

    private final UserRepository userRepository;

    @Override
    public List<InquiryEntity> getInquiriesWithId(String inquirerId) {
        return inquiryRepository.findAllByInquirer_IdOrderByInquiredAtDesc(inquirerId);
    }

    @Override
    public List<InquiryEntity> getInquiriesWithIdAndType(String inquirerId, Integer typeId) {
        return inquiryRepository.findAllByInquirer_IdAndAndType_IdOrderByInquiredAtDesc(inquirerId, typeId);
    }

    @Override
    public List<InquiryEntity> getInquiriesWithIsAnsweredFalse() {
        return inquiryRepository.findAllByIsAnsweredEqualsOrderByInquiredAtDesc(0);
    }

    @Override
    public Long getMaxInquiryId() {
        return inquiryRepository
                .findAll()
                .stream()
                .max(Comparator.comparingLong(InquiryEntity::getId))
                .map(InquiryEntity::getId)
                .orElse(1L);
    }

    @Override
    public InquiryEntity getInquiry(Long id) {
        return inquiryRepository.findById(id)
                .orElseThrow(InquiryNotFoundException::new);
    }

    @Override
    public boolean inquire(String title, Integer type, String inquirer, String content) {
        InquiryTypeEntity inquiryType = inquiryTypeRepository.findById(type)
                .orElseThrow(InquiryTypeNotFoundException::new);

        InquiryEntity target = new InquiryEntity();
        target.setId(getMaxInquiryId() + 1L);
        target.setType(inquiryType);
        target.setInquirer(userRepository.findById(inquirer)
                .orElseThrow(UserNotFoundException::new));
        target.setInquiredAt(LocalDate.now());
        target.setTitle(title);
        target.setContent(content);
        target.setIsAnswered(0);

        inquiryRepository.saveAndFlush(target);
        return true;
    }
}
