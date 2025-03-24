package com.example.case_study.repository;

import com.example.case_study.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserRepository extends JpaRepository<Users,Long> {
    @Query(value = "select * from Users where user_name = :userName and pass = :pass and block_account = false", nativeQuery = true)
    Users findByUserNameAndPass(@Param("userName") String userName, @Param("pass") String pass);
    boolean existsUsersByUserName(String userName);
    List<Users> findAllByBlockAccountFalse();
    Users findByIdAndBlockAccountFalse(Long id);
    Users findByEmail(String email);
    Users findByUserName(String userName);

}
