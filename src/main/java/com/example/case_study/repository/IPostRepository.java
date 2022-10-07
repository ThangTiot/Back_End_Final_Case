package com.example.case_study.repository;

import com.example.case_study.model.Comments;
import com.example.case_study.model.Posts;
import com.example.case_study.model.Users;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Posts, Long> {
    @Query(value = "select * from Posts where is_deleted = false ", nativeQuery = true)
    List<Posts> findAllCustom();

    @Query(value = "select * from Posts where users_id like :id and idelete = false",nativeQuery = true)
    List<Posts> findAllByUsersAndIDeleteFalse(@Param("id") Long id);
}
