package com.example.case_study.controller;

import com.example.case_study.model.User;
import com.example.case_study.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/logIn")
public class LogInController {
    @Autowired
    IUserService iUserService;

    @PostMapping("/login")
    public ResponseEntity<User> logIn(@RequestBody User users) {
        return ResponseEntity.ok(iUserService.create(users));
    }

    @PostMapping("/signUp")
    public ResponseEntity<User> signUp(@RequestBody User users) {
        return ResponseEntity.ok(iUserService.register(users));
    }
}
