package com.example.case_study.controller;

import com.example.case_study.model.User;
import com.example.case_study.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    IUserService iUserService;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(iUserService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        Optional<User> users = iUserService.findById(id);
        if (users.isPresent()) {
            return new ResponseEntity<>(users.get(), HttpStatus.OK);
        }
        return null;
    }

    @PutMapping("/block/{id}")
    public void blockAccount(@PathVariable Long id) {
        iUserService.delete(id);
    }
}
