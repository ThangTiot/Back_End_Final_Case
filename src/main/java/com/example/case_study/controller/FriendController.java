package com.example.case_study.controller;

import com.example.case_study.model.FriendList;
import com.example.case_study.service.IFriendListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    IFriendListService iFriendListService;

    @GetMapping("/findAllByUser/{id}")
    public ResponseEntity<List<FriendList>> findAllFriend(@PathVariable Long id) {
        return new ResponseEntity<>(iFriendListService.findAllFriend(id), HttpStatus.OK);
    }
}
