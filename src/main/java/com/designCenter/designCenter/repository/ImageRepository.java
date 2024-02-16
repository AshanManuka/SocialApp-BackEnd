package com.designCenter.designCenter.repository;

import com.designCenter.designCenter.entity.SampleImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<SampleImage,Long> {
}
