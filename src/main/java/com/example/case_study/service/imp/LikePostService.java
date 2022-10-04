package com.example.case_study.service.imp;

import com.example.case_study.model.LikePost;
import com.example.case_study.model.Posts;
import com.example.case_study.repository.ILikePostRepository;
import com.example.case_study.service.ILikePostService;
import com.example.case_study.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikePostService implements ILikePostService {
    @Autowired
    ILikePostRepository iLikePostRepository;

    @Autowired
    IPostService iPostService;

    @Override
    public List<LikePost> findAll() {
        return iLikePostRepository.findAll();
    }

    @Override
    public LikePost save(LikePost likePost) {
        LikePost likePost1 = iLikePostRepository.save(likePost);
        Long idPost = likePost1.getPost().getId();
        Posts posts = iPostService.findById(idPost);
        Long presentLike = posts.getLikeCount();
        posts.setLikeCount(presentLike + 1);
        iPostService.save(posts);
        return likePost1;
    }

    @Override
    public void delete(Long id) {
        Optional<LikePost> likePost = findById(id);
        if (likePost.isPresent()) {
            Long idPost = likePost.get().getPost().getId();
            Posts posts = iPostService.findById(idPost);
            Long presentLike = posts.getLikeCount();
            posts.setLikeCount(presentLike - 1);
            iPostService.save(posts);
        }
        iLikePostRepository.deleteById(id);
    }

    @Override
    public Optional<LikePost> findById(Long id) {
        return iLikePostRepository.findById(id);
    }
}
