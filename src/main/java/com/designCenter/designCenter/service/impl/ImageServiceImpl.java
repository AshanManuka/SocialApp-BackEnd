package com.designCenter.designCenter.service.impl;

import com.designCenter.designCenter.constant.CommonConstant;
import com.designCenter.designCenter.dto.common.CustomServiceException;
import com.designCenter.designCenter.dto.image.ImageReqDto;
import com.designCenter.designCenter.entity.Category;
import com.designCenter.designCenter.entity.Image;
import com.designCenter.designCenter.entity.User;
import com.designCenter.designCenter.enums.ActiveStatus;
import com.designCenter.designCenter.repository.CategoryRepository;
import com.designCenter.designCenter.repository.ImageRepository;
import com.designCenter.designCenter.repository.UserRepository;
import com.designCenter.designCenter.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Log4j2
@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public String uploadImage(ImageReqDto reqDto, Long userId, Long categoryId) throws IOException {
        log.info("Finding the user by Id");
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomServiceException(CommonConstant.NotFoundConstants.NO_USER_FOUND));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CustomServiceException(CommonConstant.NotFoundConstants.NO_CATEGORY_FOUND));

        Image sImage = Image.builder()
                .title(reqDto.getTitle())
                .description(reqDto.getDescription())
                .picture(reqDto.getImage().getBytes())
                .uploaded(new Date())
                .updated(new Date())
                .activeStatus(ActiveStatus.ACTIVE)
                .user(user)
                .category(category)
                .build();

        imageRepository.save(sImage);
        return "saved";
    }
}
