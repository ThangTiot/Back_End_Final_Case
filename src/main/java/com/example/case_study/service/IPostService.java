package com.example.case_study.service;

import com.example.case_study.model.Posts;

import java.util.List;

public interface IPostService extends ICommon<Posts>{
    Posts findById(Long id);
    List<Posts> listPostOfNewFeed(Long id);

    List<Posts> listPostOfTimeLine(Long id, Long id2);

    List<Posts> findPostListByUser(Long id);
}
