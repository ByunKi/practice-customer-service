package com.practice.repository;

import com.practice.domain.entity.InquiryEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<InquiryEntity, Long> {
    List<InquiryEntity> findAllByInquirer_IdOrderByInquiredAtDesc(String id);

    List<InquiryEntity> findAllByInquirer_IdAndAndType_IdOrderByInquiredAtDesc(String id, Integer typeId);

    List<InquiryEntity> findAllByIsAnsweredEqualsOrderByInquiredAtDesc(Integer value);

}
