package com.example.case_study.service;

import com.example.case_study.model.LikeComment;
import com.example.case_study.model.LikePost;

import java.util.List;
import java.util.Optional;

public interface ILikeCommentService extends ICommon<LikeComment>{

    Optional<LikeComment> findById(Long id);

    List<LikeComment> findAllByUser(Long idUser);
}
