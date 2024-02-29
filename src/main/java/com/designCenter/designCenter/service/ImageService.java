package com.designCenter.designCenter.service;

import com.designCenter.designCenter.dto.image.ImageReqDto;
import com.designCenter.designCenter.dto.image.ImageResDto;
import com.designCenter.designCenter.dto.user.UserBasicResDto;

import java.io.IOException;
import java.util.List;

public interface ImageService {

    String uploadImage(ImageReqDto reqDto, Long userId, Long categoryId) throws IOException;

    List<ImageResDto> getImagesByUser(Long userId);

    byte[] getSingleImagesByUser(Long userId, Long imageId);

    byte[] getUserProfileImage(Long userId);

    List<ImageResDto> getTodayImages();

    List<ImageResDto> searchImage(String keyword);

    UserBasicResDto getUserProfileDetail(Long userId);
}
