package com.designCenter.designCenter.controller;

import com.designCenter.designCenter.dto.common.CommonResponse;
import com.designCenter.designCenter.dto.user.UserReqDto;
import com.designCenter.designCenter.dto.user.UserResDto;
import com.designCenter.designCenter.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping(value = "/public")
public class PublicController {

    private final UserService userService;

    @PostMapping(value = "/sign-up")
    public ResponseEntity<?> userSignUp(@ModelAttribute UserReqDto reqDto) throws IOException {
        log.info("New User sign-up, email :{}",reqDto.getEmail());
        UserResDto userResponse = userService.userSignUp(reqDto);
        log.info("Saved User details");
        return ResponseEntity.ok(new CommonResponse<>(true,userResponse));
    }

//    @PostMapping(value = "/sign-up")
//    public ResponseEntity<?> userSignUp(@ModelAttribute SampleSignUpDto reqDto) throws IOException {
//        String result = userService.sampleSignUp(reqDto);
//        return ResponseEntity.ok(new CommonResponse<>(true,result));
//    }
//
//    @GetMapping(value = "/retrieve/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
//    public ResponseEntity<byte[]> retrieveImage(@PathVariable Long id) {
//        try {
//            byte[] imageData = userService.getImageById(id);
//            return ResponseEntity.ok().body(imageData);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }

}
