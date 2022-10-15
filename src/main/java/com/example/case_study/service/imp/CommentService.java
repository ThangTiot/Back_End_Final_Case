package com.example.case_study.service.imp;

import com.example.case_study.model.Comments;
import com.example.case_study.model.LikePost;
import com.example.case_study.model.Posts;
import com.example.case_study.model.Users;
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
        return iCommentRepository.findAllCommentParent();
    }

    @Override
    public Comments save(Comments comments) {
        if (comments.getId() == null) {
            Long idPost = comments.getPosts().getId();
            Posts posts = iPostService.findById(idPost);
            Long commentCountPresent = posts.getCommentCount();
            posts.setCommentCount(commentCountPresent + 1);
            iPostService.save(posts);
        }
        return iCommentRepository.save(comments);
    }

    @Override
    public void delete(Long id) {
        Optional<Comments> comments = findById(id);
        if (comments.isPresent()) {
            comments.get().setIsDelete(true);
            Long idPost = comments.get().getPosts().getId();
            Posts posts = iPostService.findById(idPost);
            Long commentCountPresent = posts.getCommentCount();
            commentCountPresent -= 1;
            List<Comments> cmtChild = findAllByIdParent(id);
            for (Comments cmt : cmtChild) {
                cmt.setIsDelete(true);
                commentCountPresent -= 1;
            }
            posts.setCommentCount(commentCountPresent);
            iPostService.save(posts);
            iCommentRepository.save(comments.get());
        }
    }

    @Override
    public Optional<Comments> findById(Long id) {
        return iCommentRepository.findById(id);
    }

    @Override
    public Comments updateComment(Comments comments, Long id) {
        Comments comments1 = findById(id).orElse(null);
            comments1.setContent(comments.getContent());
            return iCommentRepository.save(comments1);
     }


    @Override
    public List<Comments> findAllByPost(Long idPost) {
        return iCommentRepository.findAllByPostsId(idPost);
    }

    @Override
    public List<Comments> findAllCommentChild() {
        return iCommentRepository.findAllCommentChild();
    }

    @Override
    public List<Comments> findAllByIdParent(Long idParentCmt) {
        return iCommentRepository.findAllByIdParentCommentAndIsDeleteFalse(idParentCmt);
    }
}
