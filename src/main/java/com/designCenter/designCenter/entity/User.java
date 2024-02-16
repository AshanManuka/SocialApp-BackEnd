package com.designCenter.designCenter.entity;

import com.designCenter.designCenter.enums.ActiveStatus;
import com.designCenter.designCenter.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String userName;

    @Lob
    private byte[] profileImage;
    private String email;
    private String password;
    private Date registered;
    private Date updated;

    @Enumerated(value = EnumType.STRING)
    private ActiveStatus status;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Image> images = new ArrayList<>();
}
