package com.example.case_study.service;

import com.example.case_study.model.Comments;

import java.util.List;
import java.util.Optional;

public interface ICommentService extends ICommon<Comments>{
    Optional<Comments> findById(Long id);
   Comments updateComment(Comments comments,Long id);
    List<Comments> findAllByPost(Long idPost);
    List<Comments> findAllCommentChild();
    List<Comments> findAllByIdParent(Long idParentCmt);
}
