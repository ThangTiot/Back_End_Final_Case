package com.example.case_study.service.imp;

import com.example.case_study.model.Comments;
import com.example.case_study.model.LikeComment;
import com.example.case_study.repository.ILikeCommentRepository;
import com.example.case_study.repository.ILikePostRepository;
import com.example.case_study.service.ICommentService;
import com.example.case_study.service.ILikeCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeCommentService implements ILikeCommentService {
    @Autowired
    ILikeCommentRepository iLikeCommentRepository;

    @Autowired
    ICommentService iCommentService;

    @Override
    public List<LikeComment> findAll() {
        return iLikeCommentRepository.findAll();
    }

    @Override
    public LikeComment save(LikeComment likeComment) {
        LikeComment likeComment1 = iLikeCommentRepository.save(likeComment);
        Long idComment = likeComment1.getComments().getId();
        Comments comments = iCommentService.findById(idComment).orElse(null);
        Long presentLike = comments.getLikeCount();
        comments.setLikeCount(presentLike + 1);
        iCommentService.save(comments);
        return likeComment1;
    }

    @Override
    public void delete(Long id) {
       Optional<LikeComment> likeComment = findById(id);
       if (likeComment.isPresent()){
           Long idComment = likeComment.get().getComments().getId();
           Comments comments = iCommentService.findById(idComment).orElse(null);
           Long presentLike = comments.getLikeCount();
           comments.setLikeCount(presentLike - 1);
           iCommentService.save(comments);
       }
       iLikeCommentRepository.deleteById(id);
    }

    @Override
    public Optional<LikeComment> findById(Long id) {
        return iLikeCommentRepository.findById(id);
    }

    @Override
    public List<LikeComment> findAllByUser(Long idUser) {
        return iLikeCommentRepository.findAllByUser(idUser);
    }
}
