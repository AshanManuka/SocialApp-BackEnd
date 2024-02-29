package com.designCenter.designCenter.service.impl;

import com.designCenter.designCenter.constant.CommonConstant;
import com.designCenter.designCenter.dto.common.CustomServiceException;
import com.designCenter.designCenter.dto.image.ImageReqDto;
import com.designCenter.designCenter.dto.image.ImageResDto;
import com.designCenter.designCenter.dto.user.UserBasicResDto;
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
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public String uploadImage(ImageReqDto reqDto, Long userId, Long categoryId) throws IOException {
        log.info("Finding the user by Id");
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomServiceException(CommonConstant.NotFoundConstants.NO_USER_FOUND));
        log.info("got user email: {}",user.getEmail());
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CustomServiceException(CommonConstant.NotFoundConstants.NO_CATEGORY_FOUND));
        log.info("selected category id:{}",category.getId());

        log.info("Dto :{}",reqDto);

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

        log.info("Ready to save");

        imageRepository.save(sImage);
        return "saved";
    }

    @Override
    public List<ImageResDto> getImagesByUser(Long userId) {
        log.info("Finding the user by Id");
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomServiceException(CommonConstant.NotFoundConstants.NO_USER_FOUND));
        log.info("check & load images fom db");
        List<Image> images = imageRepository.getAllImagesByUser(user.getId());
        if(images.isEmpty()) throw new CustomServiceException(CommonConstant.NotFoundConstants.NO_IMAGE_FOUND);
        return images
                .stream()
                .map(image -> modelMapper.map(image, ImageResDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public byte[] getSingleImagesByUser(Long userId, Long imageId) {
        Optional<Image> optionalImageEntity = imageRepository.findById(imageId);
        if (optionalImageEntity.isPresent()) {
            Image imageEntity = optionalImageEntity.get();
            return imageEntity.getPicture();
        }
        return null;
    }

    @Override
    public byte[] getUserProfileImage(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomServiceException(CommonConstant.NotFoundConstants.NO_USER_FOUND));
        if(user.getProfileImage().length != 0){
            return user.getProfileImage();
        }else{
            return new byte[0];
        }
    }

    @Override
    public List<ImageResDto> getTodayImages() {
        log.info("Getting today Images for feed");
        List<Image> images = imageRepository.getImagesForMainFeed();
        if(images.isEmpty()) throw new CustomServiceException(CommonConstant.NotFoundConstants.NO_IMAGE_FOUND);
        return images
                .stream()
                .map(image -> modelMapper.map(image, ImageResDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ImageResDto> searchImage(String keyword) {
        log.info("Getting Images by KeyWord");
        List<Image> images = imageRepository.searchImageByKeyword(keyword);
        if(images.isEmpty()) throw new CustomServiceException(CommonConstant.NotFoundConstants.NO_IMAGE_FOUND);
        return images
                .stream()
                .map(image -> modelMapper.map(image, ImageResDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserBasicResDto getUserProfileDetail(Long userId) {
        log.info("Check Is user exists, UserId:{}",userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomServiceException(CommonConstant.NotFoundConstants.NO_USER_FOUND));
        UserBasicResDto response = modelMapper.map(user,UserBasicResDto.class);
        log.info(response);

        return response;
    }


}
