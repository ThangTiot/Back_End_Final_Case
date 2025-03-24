package com.example.case_study.controller;

import com.example.case_study.dto.UserDto;
import com.example.case_study.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogInController {

    @Autowired
    IUserService iUserService;

    @GetMapping("/userGg")
    public ResponseEntity<Long> getUserInfo(@AuthenticationPrincipal OAuth2User principal) {
        UserDto userDto = iUserService.findByEmail(principal.getAttribute("email"));
        if (userDto == null) {
            userDto = iUserService.registUserLoginGoogle(principal);
        }
        return ResponseEntity.ok(userDto.getId());
    }

//    @PostMapping("/logIn")
//    public ResponseEntity<UserDto> logIn(@RequestBody UserDto userDto) {
//        UserDto userDto1 = iUserService.checkSignIn(userDto);
//        if (userDto1 != null) {
//            return ResponseEntity.ok(userDto1);
//        }
//        return null;
//    }

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody UserDto userDto) {
        String checkRequired = iUserService.checkRequired(userDto);
        String check = iUserService.checkSignUpUserName(userDto);
        if (checkRequired != null) {
            return ResponseEntity.ok(checkRequired);
        }
        if (check != null) {
            return ResponseEntity.ok(check);
        }
        iUserService.save(userDto);
        return null;
    }
}
