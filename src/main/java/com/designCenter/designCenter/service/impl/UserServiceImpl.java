package com.designCenter.designCenter.service.impl;

import com.designCenter.designCenter.constant.CommonConstant;
import com.designCenter.designCenter.dto.SampleSignUpDto;
import com.designCenter.designCenter.dto.common.CustomServiceException;
import com.designCenter.designCenter.dto.user.UserReqDto;
import com.designCenter.designCenter.dto.user.UserResDto;
import com.designCenter.designCenter.entity.SampleImage;
import com.designCenter.designCenter.entity.User;
import com.designCenter.designCenter.enums.ActiveStatus;
import com.designCenter.designCenter.repository.ImageRepository;
import com.designCenter.designCenter.repository.UserRepository;
import com.designCenter.designCenter.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;


@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserResDto userSignUp(UserReqDto reqDto) throws IOException {
        User user = userRepository.findByEmail(reqDto.getEmail());
        log.info("Checking, is email already exists..");
        if(user != null) throw new CustomServiceException(CommonConstant.DuplicateConstants.EMAIL_ALREADY_EXIST);

        user = User.builder()
                .name(reqDto.getName())
                .email(reqDto.getEmail())
                .userName(reqDto.getUserName())
                .password(reqDto.getPassword())
                .registered(new Date())
                .updated(new Date())
                .status(ActiveStatus.PENDING)
                .build();

        if(reqDto.getProfileImage() != null && !reqDto.getProfileImage().isEmpty()){
            user.setProfileImage(reqDto.getProfileImage().getBytes());
        }

        User savedUser = userRepository.save(user);
        log.info("Saving User email:{} by Id:{} ",savedUser.getEmail(),savedUser.getId());
        return this.modelMapper.map(savedUser,UserResDto.class);
    }

    @Override
    public String sampleSignUp(SampleSignUpDto dto) throws IOException {
        SampleImage sImage = SampleImage.builder()
                .imageName(dto.getName())
                .imageData(dto.getImage().getBytes())
                .build();
        imageRepository.save(sImage);
        return sImage.getImageName();
    }

    @Override
    public byte[] getImageById(Long id) {
        Optional<SampleImage> optionalImageEntity = imageRepository.findById(id);
        if (optionalImageEntity.isPresent()) {
            SampleImage imageEntity = optionalImageEntity.get();
            return imageEntity.getImageData();
        }
        return null;
    }

}
