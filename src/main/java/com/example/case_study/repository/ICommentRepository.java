package com.example.case_study.repository;

import com.example.case_study.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepository extends JpaRepository<Comment,Long> {
    @Query(value = "select * from Comments where delete_comment = true ", nativeQuery = true)
    List<Comment> findAllCustom();
}
