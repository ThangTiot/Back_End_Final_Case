package com.example.case_study.controller;

import com.example.case_study.model.Posts;
import com.example.case_study.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/findPostById/{id}")
    public ResponseEntity<List<Posts>> findPostById(@PathVariable Long id){
        List<Posts> posts = iPostService.findPostListByUser(id);
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(posts);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        iPostService.delete(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Posts> update(@PathVariable Long id, @RequestBody Posts posts) {
        Posts posts1 = iPostService.findById(id);
        posts1.setPermissionPost(posts.getPermissionPost());
        posts1.setContent(posts.getContent());
        posts1.setImageName(posts.getImageName());
        return new ResponseEntity<>(iPostService.save(posts1), HttpStatus.CREATED);
    }

    @GetMapping("/post-of-user/{id}")
    public ResponseEntity<List<Posts>> findAllPostsByUser(@PathVariable Long id){
        List<Posts> posts = iPostService.findPostListByUser(id);
        if(posts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(posts);
    }

}

