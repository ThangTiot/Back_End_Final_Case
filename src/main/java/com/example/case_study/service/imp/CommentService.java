package com.example.case_study.service.imp;

import com.example.case_study.model.Comments;
import com.example.case_study.model.LikePost;
import com.example.case_study.model.Posts;
import com.example.case_study.repository.ICommentRepository;
import com.example.case_study.service.ICommentService;
import com.example.case_study.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements ICommentService {
    @Autowired
    ICommentRepository iCommentRepository;
    @Autowired
    IPostService iPostService;

    @Override
    public List<Comments> findAll() {
        return iCommentRepository.findAllCustom();
    }

    @Override
    public Comments save(Comments comments) {
        Comments comments1 = iCommentRepository.save(comments);
        Long idPost = comments1.getPosts().getId();
        Posts posts = iPostService.findById(idPost);
        Long commentCountPresent = posts.getCommentCount();
        posts.setCommentCount(commentCountPresent + 1);
        iPostService.save(posts);
        return comments1;
    }

    @Override
    public void delete(Long id) {
        Optional<Comments> comments = findById(id);
        if (comments.isPresent()) {
            comments.get().setIsDelete(true);
            Long idPost = comments.get().getPosts().getId();
            Posts posts = iPostService.findById(idPost);
            Long commentCountPresent = posts.getCommentCount();
            posts.setCommentCount(commentCountPresent - 1);
            iPostService.save(posts);
            iCommentRepository.save(comments.get());
        }
    }

    @Override
    public Optional<Comments> findById(Long id) {
        return iCommentRepository.findById(id);
    }

    @Override
    public List<Comments> findAllByPost(Long idPost) {
        return iCommentRepository.findAllByPostsId(idPost);
    }
}
