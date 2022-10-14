package com.example.case_study.repository;

import com.example.case_study.model.Comments;
import com.example.case_study.model.LikeComment;
import com.example.case_study.model.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ILikeCommentRepository extends JpaRepository<LikeComment,Long> {
    @Query(value = "select * from like_comment where users_id = :id", nativeQuery = true)
    List<LikeComment> findAllByUser(@Param("id") Long idUser);
}
