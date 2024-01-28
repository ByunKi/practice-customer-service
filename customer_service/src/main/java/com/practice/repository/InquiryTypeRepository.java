package com.practice.repository;

import com.practice.domain.entity.InquiryTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryTypeRepository extends JpaRepository<InquiryTypeEntity, Integer> {

}
