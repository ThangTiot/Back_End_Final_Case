package com.example.case_study.controller;

import com.example.case_study.dto.UserDto;
import com.example.case_study.model.CustomUserDetail;
import com.example.case_study.model.Users;
import com.example.case_study.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    IUserService iUserService;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(iUserService.findAll());
    }

    @GetMapping("/info")
    public ResponseEntity<UserDto> getUserInfo(@AuthenticationPrincipal CustomUserDetail customUserDetail) {
        UserDto userDto = new UserDto(customUserDetail.getUser());
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(iUserService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id,@RequestBody UserDto userDto ){
        return ResponseEntity.ok(iUserService.updateUserInfo(userDto, id));
    }
    @DeleteMapping("/{id}/block")
    public void blockAccount(@PathVariable Long id) {
        iUserService.delete(id);
    }
}
