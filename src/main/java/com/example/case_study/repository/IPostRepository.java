package com.example.case_study.repository;
import com.example.case_study.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IPostRepository extends JpaRepository<Posts, Long> {
//    @Query(value = "select * from Posts where delete_post = true ", nativeQuery = true)
//    List<Posts> findAllCustom();

    @Query(value = "select * from posts  where users_id like :id and i_delete = false ", nativeQuery = true)
    List<Posts> findPostById(@Param("id") Long id);
}
