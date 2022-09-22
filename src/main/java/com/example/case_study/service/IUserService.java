package com.example.case_study.service;

import com.example.case_study.model.Users;

import java.util.Optional;

public interface IUserService extends ICommon<Users>{

    Optional<Users> findById(Long id);
    Users checkSignIn(Users users);

    Users checkSignUpUserName(Users users);
}
