package com.example.case_study.service;

import com.example.case_study.model.User;

import java.util.Optional;

public interface IUserService extends ICommon<User>{

    Optional<User> findById(Long id);
    User checkSignIn(User users);

    User checkSignUpUserName(User users);

    User register(User users);

    User create(User users);
}
