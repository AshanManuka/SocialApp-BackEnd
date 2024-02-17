package com.designCenter.designCenter.controller;

import com.designCenter.designCenter.dto.common.CommonResponse;
import com.designCenter.designCenter.dto.image.ImageReqDto;
import com.designCenter.designCenter.dto.image.ImageResDto;
import com.designCenter.designCenter.dto.user.TempToken;
import com.designCenter.designCenter.service.ImageService;
import com.designCenter.designCenter.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
@Log4j2
public class AppUserController {

    private final UserService userService;
    private final ImageService imageService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> checkCredentials(@RequestParam String userName, @RequestParam String password){
    log.info("Check credentials");
    TempToken tempToken = userService.checkCredentials(userName,password);
    return ResponseEntity.ok(new CommonResponse<>(true,tempToken));
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<?> uploadImage(@ModelAttribute ImageReqDto reqDto,
                                         @RequestParam Long userId,
                                         @RequestParam Long categoryId) throws IOException {
        log.info("Uploading new Image from User: {}",userId);
        String status = imageService.uploadImage(reqDto,userId,categoryId);
        return ResponseEntity.ok(new CommonResponse<>(true,status));
    }

    @GetMapping(value = "/my-images")
    public ResponseEntity<?> getImagesByUser(@RequestParam Long userId){
        log.info("Load all image by User, UserId: {}",userId);
        List<ImageResDto> imageList = imageService.getImagesByUser(userId);
        return ResponseEntity.ok(new CommonResponse<>(true,imageList));
    }

    @GetMapping(value = "/my-single-image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getSingleImagesByUser(@RequestParam Long userId, @RequestParam Long imageId){
        log.info("Load single image by User, UserId: {}",userId);
        byte[] image = imageService.getSingleImagesByUser(userId,imageId);
        return ResponseEntity.ok().body(image);
    }




}
