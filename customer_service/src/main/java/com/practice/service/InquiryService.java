package com.practice.service;

import com.practice.domain.entity.InquiryEntity;
import com.practice.domain.entity.InquiryTypeEntity;
import com.practice.domain.enums.InquiryType;
import java.util.List;

public interface InquiryService {
    List<InquiryEntity> getInquiriesWithId(String inquirerId);

    List<InquiryEntity> getInquiriesWithIdAndType(String inquirerId, Integer typeId);

    List<InquiryEntity> getInquiriesWithIsAnsweredFalse();

    Long getMaxInquiryId();

    InquiryEntity getInquiry(Long id);

    boolean inquire(String title, Integer type, String inquirer, String content);
}
