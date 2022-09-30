package com.example.case_study.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Long likeCount = 0L;
    private Boolean deleteComment = true;
    private LocalDateTime dateCreate = LocalDateTime.now();
    @ManyToOne
    private Posts posts;
    @ManyToOne
    private Users users;
    @ManyToOne
    private Comments parentComment;
}
