package com.example.case_study.model;

import com.example.case_study.controller.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Long likeCount = 0L;
    private Boolean isDeleted = false;
    @CreationTimestamp
    private LocalDateTime dateCreate;
    @ManyToOne
    private Post posts;
    @ManyToOne
    private User users;
    @ManyToOne
    private Comment parentComment;

    public Comment(CommentDto source) {
        BeanUtils.copyProperties(source, this);
    }

    public void update(CommentDto source) {
        BeanUtils.copyProperties(source,this);
    }
}
