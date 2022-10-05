package com.example.case_study.service;

import com.example.case_study.model.Posts;

import java.util.List;

public interface IPostService extends ICommon<Posts>{
    List<Posts> findPostById(Long id);
    Posts findById(Long id);
    List<Posts> listPostOfNewFeed(Long id);
}
