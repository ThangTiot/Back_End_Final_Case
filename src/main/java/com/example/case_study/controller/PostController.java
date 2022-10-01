package com.example.case_study.controller;

import com.example.case_study.model.Post;
import com.example.case_study.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/post")
public class PostController {
    @Autowired
    IPostService iPostService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Post>> findAll() {
        return new ResponseEntity<>(iPostService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Post> create(@RequestBody Post posts) {
        Post posts1 = iPostService.save(posts);
        return new ResponseEntity<>(posts1, HttpStatus.CREATED);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Post> findById(@PathVariable Long id) {
        Optional<Post> posts = iPostService.findById(id);
        if (posts.isPresent()) {
            return new ResponseEntity<>(posts.get(), HttpStatus.OK);
        }
        return null;
    }

    @PutMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Post> posts = iPostService.findById(id);
        if (posts.isPresent()) {
            posts.get().setIDelete(false);
            iPostService.save(posts.get());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Post> update(@RequestBody Post posts) {
        Optional<Post> posts1 = iPostService.findById(posts.getId());
        if (posts1.isPresent()) {
            posts1.get().setPermissionPost(posts.getPermissionPost());
            posts1.get().setContent(posts.getContent());
            posts1.get().setImageName(posts.getImageName());
            return new ResponseEntity<>(iPostService.save(posts1.get()), HttpStatus.CREATED);
        }
        return null;
    }
}

