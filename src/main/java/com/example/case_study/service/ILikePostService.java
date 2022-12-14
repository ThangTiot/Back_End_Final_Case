package com.example.case_study.service;

import com.example.case_study.model.LikePost;

import java.util.List;
import java.util.Optional;

public interface ILikePostService extends ICommon<LikePost>{
    Optional<LikePost> findById(Long id);

    List<LikePost> findAllByUser(Long idUser);
}
