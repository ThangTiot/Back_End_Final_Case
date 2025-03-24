package com.example.case_study.controller;

import com.example.case_study.model.LikePost;
import com.example.case_study.service.ILikePostService;
import com.example.case_study.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likePost")
public class LikePostController {
    @Autowired
    IPostService iPostService;
    @Autowired
    ILikePostService iLikePostService;

    @GetMapping
    public ResponseEntity<List<LikePost>> findAll() {
        return new ResponseEntity<>(iLikePostService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/findAllByUser/{idUser}")
    public ResponseEntity<List<LikePost>> findAllByUser(@PathVariable Long idUser) {
        return new ResponseEntity<>(iLikePostService.findAllByUser(idUser), HttpStatus.OK);
    }


    @PostMapping("/like")
    public ResponseEntity<LikePost> likePost(@RequestBody LikePost likePost) {
        return ResponseEntity.ok(iLikePostService.save(likePost));
    }

    @DeleteMapping("/disLike/{id}")
    public void disLikePost(@PathVariable Long id) {
        iLikePostService.delete(id);
    }
}

