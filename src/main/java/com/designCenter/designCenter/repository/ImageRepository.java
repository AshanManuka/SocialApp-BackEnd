package com.designCenter.designCenter.repository;

import com.designCenter.designCenter.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Long> {

    @Query(value = "SELECT i FROM Image i WHERE i.user.id=?1")
    List<Image> getAllImagesByUser(Long id);

    @Query(value ="SELECT * FROM image ORDER BY id DESC LIMIT 20", nativeQuery = true)
    List<Image> getImagesForMainFeed();
}
