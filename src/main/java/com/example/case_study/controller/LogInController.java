package com.example.case_study.controller;

import com.example.case_study.dto.UserDto;
import com.example.case_study.model.Users;
import com.example.case_study.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.web.servlet.OAuth2ClientDsl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/logIn")
public class LogInController {
    @Autowired
    IUserService iUserService;

    @GetMapping("/user")
    public Map<String, Object> getUserInfo(@AuthenticationPrincipal OAuth2User principal) {

        return principal.getAttributes();
    }

    @PostMapping("/logIn")
    public ResponseEntity<UserDto> logIn(@RequestBody UserDto userDto) {
        UserDto userDto1 = iUserService.checkSignIn(userDto);
        if (userDto1 != null) {
            return ResponseEntity.ok(userDto1);
        }
        return null;
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> signUp(@RequestBody UserDto userDto) {
        boolean check = iUserService.checkSignUpUserName(userDto);
        if (check){
            return ResponseEntity.ok(userDto);
        } else{
            iUserService.save(userDto);
            return null;
        }
    }
}
