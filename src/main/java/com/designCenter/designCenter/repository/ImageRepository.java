package com.designCenter.designCenter.repository;

import com.designCenter.designCenter.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
