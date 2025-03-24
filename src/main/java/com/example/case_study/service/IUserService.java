package com.example.case_study.service;

import com.example.case_study.dto.UserDto;
import com.example.case_study.model.Users;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Optional;

public interface IUserService extends ICommon<UserDto>{


    UserDto updateUserInfo(UserDto userDto, Long id);
    UserDto findById(Long id);
    UserDto checkSignIn(UserDto userDto);
    String checkSignUpUserName(UserDto userDto);
    UserDto findByEmail(String email);
    UserDto registUserLoginGoogle(OAuth2User principal);
    String checkRequired(UserDto userDto);
}
