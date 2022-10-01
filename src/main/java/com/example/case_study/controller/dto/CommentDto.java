package com.example.case_study.controller.dto;

import com.example.case_study.model.Comment;
import com.example.case_study.model.Post;
import com.example.case_study.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDto {
    @NotNull
    private Long id;
    private String content;
    private Long likeCount ;
    private Boolean isDeleted = false;
    private LocalDateTime dateCreate;
    private Post posts;
    private User users;
    private Comment parentComment;
    public CommentDto(Comment source){
        BeanUtils.copyProperties(source,this);
    }
}
