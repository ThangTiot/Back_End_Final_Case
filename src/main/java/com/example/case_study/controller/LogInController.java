package com.example.case_study.controller;

import com.example.case_study.dto.UserDto;
import com.example.case_study.dto.UserToken;
import com.example.case_study.model.Users;
import com.example.case_study.service.IUserService;
import com.example.case_study.service.imp.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/logIn")
public class LogInController {
    @Autowired
    IUserService iUserService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;

    @PostMapping("/logIn")
    public ResponseEntity<UserDto> logIn(@RequestBody UserDto userDto) {
        UserDto userDto1 = iUserService.checkSignIn(userDto);
        if (userDto1 != null) {
            return ResponseEntity.ok(userDto1);
        }
        return null;
    }

    @PostMapping("/logInSecurity")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        // Tạo ra 1 đối tượng Authentication.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUserName(), userDto.getPass()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtService.createToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserDto userDto1 = iUserService.findByUserName(userDto.getUserName());
        return ResponseEntity.ok(new UserToken(userDto1.getId(), token, userDetails.getUsername(), userDetails.getAuthorities()));
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
