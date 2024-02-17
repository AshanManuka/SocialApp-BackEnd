package com.designCenter.designCenter.entity;

import com.designCenter.designCenter.enums.ActiveStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Date uploaded;
    private Date updated;

    @Lob
    private byte[] picture;

    @Enumerated(EnumType.STRING)
    private ActiveStatus activeStatus;
    private Long likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;
}
