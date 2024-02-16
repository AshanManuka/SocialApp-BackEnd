package com.designCenter.designCenter.dto.image;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ImageReqDto {
    private String title;
    private String description;
    private MultipartFile image;
}
