package com.example.case_study.model;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
