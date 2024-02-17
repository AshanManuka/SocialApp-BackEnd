package com.designCenter.designCenter.dto.image;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ImageResDto {
    private Long id;
    private String title;
    private String description;
    private byte[] picture;
    private Long likeCount;
}
