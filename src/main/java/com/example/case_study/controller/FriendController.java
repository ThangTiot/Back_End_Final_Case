package com.example.case_study.controller;

import com.example.case_study.model.FriendList;
import com.example.case_study.model.Users;
import com.example.case_study.service.IFriendListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/friends")
public class FriendController {
    @Autowired
    IFriendListService iFriendListService;

    @GetMapping("/findAllFriendOfUser/{id}")
    public ResponseEntity<List<Users>> findAllFriend(@PathVariable Long id) {
        return new ResponseEntity<>(iFriendListService.findFriendOfUser(id), HttpStatus.OK);
    }
    @GetMapping("/findAllFriendOfUserConfirm/{id}")
    public ResponseEntity<List<Users>> findAllFriendConfirm(@PathVariable Long id) {
        return new ResponseEntity<>(iFriendListService.findFriendOfUserConfirm(id), HttpStatus.OK);
    }
    @GetMapping("/findAllUserNotFriend/{id}")
    public ResponseEntity<List<Users>> findAllUserNotFriend(@PathVariable Long id){
        return new ResponseEntity<>(iFriendListService.findAllUserNotFriend(id),HttpStatus.OK);
    }
}
