package com.example.case_study.repository;

import com.example.case_study.model.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ILikePostRepository extends JpaRepository<LikePost, Long> {
    @Query(value = "select * from like_post where users_id = :id", nativeQuery = true)
    List<LikePost> findAllByUser(@Param("id") Long idUser);
}
