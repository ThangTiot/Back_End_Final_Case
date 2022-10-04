package com.example.case_study.controller;

import com.example.case_study.model.LikePost;
import com.example.case_study.model.Posts;
import com.example.case_study.model.Users;
import com.example.case_study.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/posts")
public class PostController {
    @Autowired
    IPostService iPostService;

    @GetMapping
    public ResponseEntity<List<Posts>> findAll() {
        return new ResponseEntity<>(iPostService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/listPostOfNewFeed/{id}")
    public ResponseEntity<List<Posts>> listPostOfNewFeed(@PathVariable Long id) {
        return new ResponseEntity<>(iPostService.listPostOfNewFeed(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Posts> create(@RequestBody Posts posts) {
        Posts posts1 = iPostService.save(posts);
        return new ResponseEntity<>(posts1, HttpStatus.CREATED);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Posts> findById(@PathVariable Long id) {
        return new ResponseEntity<>(iPostService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        Posts posts = iPostService.findById(id);
        posts.setIsDeleted(true);
        iPostService.save(posts);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Posts> update(@PathVariable Long id, @RequestBody Posts posts) {
        Posts posts1 = iPostService.findById(id);
        posts1.setPermissionPost(posts.getPermissionPost());
        posts1.setContent(posts.getContent());
        if (posts.getImageName() != null) {
            posts1.setImageName(posts.getImageName());
        }
        return new ResponseEntity<>(iPostService.save(posts1), HttpStatus.CREATED);
    }
}

