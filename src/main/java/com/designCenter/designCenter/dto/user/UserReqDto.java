package com.designCenter.designCenter.dto.user;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserReqDto {
    private String name;
    private MultipartFile profileImage;
    private String email;
    private String userName;
    private String password;

}
