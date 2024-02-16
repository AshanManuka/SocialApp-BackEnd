package com.designCenter.designCenter.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SampleSignUpDto {
    private String name;
    private MultipartFile image;
}
