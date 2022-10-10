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

    @GetMapping("/findAllFriendOfUserConfirmTo/{id}")
    public ResponseEntity<List<Users>> findAllFriendConfirmTo(@PathVariable Long id) {
        return new ResponseEntity<>(iFriendListService.findFriendOfUserConfirmTo(id), HttpStatus.OK);
    }
    @GetMapping("/findAllFriendOfUserConfirmFrom/{id}")
    public ResponseEntity<List<Users>> findAllFriendConfirmFrom(@PathVariable Long id) {
        return new ResponseEntity<>(iFriendListService.findFriendOfUserConfirmFrom(id), HttpStatus.OK);
    }

    @GetMapping("/findAllUserNotFriend/{id}")
    public ResponseEntity<List<Users>> findAllUserNotFriend(@PathVariable Long id) {
        return new ResponseEntity<>(iFriendListService.findAllUserNotFriend(id), HttpStatus.OK);
    }

    @GetMapping("/find-all-mutual-friend/{idUserPresent}/{idUserClick} ")
    public ResponseEntity<List<Users>> findAllMutualFriend(@PathVariable Long idUserPresent, @PathVariable Long idUserClick) {
        return new ResponseEntity<>(iFriendListService.findAllMutualFriend(idUserPresent, idUserClick), HttpStatus.OK);
    }

    @PostMapping("/addFriend")
    public ResponseEntity<FriendList> addFriend(@RequestBody FriendList friendList) {
        return ResponseEntity.ok(iFriendListService.save(friendList));
    }

    @DeleteMapping("/unfriend/{idUserFrom}/{idUserTo}")
    public ResponseEntity<FriendList> unfriend(@PathVariable Long idUserFrom, @PathVariable Long idUserTo) {
        return ResponseEntity.ok(iFriendListService.deleteByUserToAndUserFrom(idUserFrom,idUserTo));
    }
    @PutMapping("/confirm/{idUserFrom}/{idUserTo}")
    public ResponseEntity<FriendList> confirm(@PathVariable Long idUserFrom, @PathVariable Long idUserTo) {
        return ResponseEntity.ok(iFriendListService.confirm(idUserFrom, idUserTo));
    }

    @PostMapping("/addFriend")
    public ResponseEntity<FriendList> addFriend(@RequestBody FriendList friendList){
        FriendList newFriend = iFriendListService.save(friendList);
        return new ResponseEntity<>(newFriend,HttpStatus.CREATED);
    }

    @DeleteMapping("/unfriend/{id}")
    public ResponseEntity<?> unfriend (@PathVariable("id") Long id){
        iFriendListService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
