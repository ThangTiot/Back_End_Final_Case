package com.example.case_study.service;

import com.example.case_study.dto.UserDto;
import com.example.case_study.model.Users;

import java.util.Optional;

public interface IUserService extends ICommon<UserDto>{


    UserDto updateUserInfo(UserDto userDto, Long id);
    UserDto findById(Long id);
    UserDto checkSignIn(UserDto userDto);
    boolean checkSignUpUserName(UserDto userDto);
}
