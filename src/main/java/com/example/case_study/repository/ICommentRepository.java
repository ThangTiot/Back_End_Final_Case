package com.example.case_study.repository;

import com.example.case_study.model.Comments;
import com.example.case_study.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICommentRepository extends JpaRepository<Comments,Long> {
    @Query(value = "select * from Comments where is_delete = false ", nativeQuery = true)
    List<Comments> findAllCustom();

    List<Comments> findAllByPostsId(Long idPost);
}
