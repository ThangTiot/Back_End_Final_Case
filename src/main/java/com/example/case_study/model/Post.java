package com.example.case_study.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String imageName;
    private Long likeCount = 0L;
    private String permissionPost = "Public";
    private LocalDateTime createDate = LocalDateTime.now();
    private Boolean iDelete = true;
    @ManyToOne
    private User users;
}
