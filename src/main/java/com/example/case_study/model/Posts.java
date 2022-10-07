package com.example.case_study.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String imageName;
    private Long likeCount = 0L;
    private Long commentCount = 0L;
    private String permissionPost = "Public";
    @CreationTimestamp
    private LocalDateTime createDate;
    private Boolean isDeleted = false;
    @ManyToOne
    private Users users;
}