package com.designCenter.designCenter.dto.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserBasicResDto {
    private Long id;
    private String name;
    private String profileImage;
    private String email;
}
