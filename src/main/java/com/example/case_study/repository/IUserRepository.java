package com.example.case_study.repository;

import com.example.case_study.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Long> {
    boolean existsByUserNameAndPass(String userName, String pass);

    User findFirstByUserNameAndPass(String userName, String pass);

    boolean existsByUserName(String userName);
}
