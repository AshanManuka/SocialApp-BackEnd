package com.designCenter.designCenter.service;

import com.designCenter.designCenter.dto.image.ImageReqDto;

import java.io.IOException;

public interface ImageService {
    String uploadImage(ImageReqDto reqDto, Long userId, Long categoryId) throws IOException;
}
