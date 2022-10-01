package com.example.case_study.service;

import com.example.case_study.model.Post;

import java.util.Optional;

public interface IPostService extends ICommon<Post>{
    Optional<Post> findById(Long id);
}
