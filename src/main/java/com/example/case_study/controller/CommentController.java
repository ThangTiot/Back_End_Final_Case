package com.example.case_study.controller;

import com.example.case_study.model.Comments;
import com.example.case_study.model.LikePost;
import com.example.case_study.model.Posts;
import com.example.case_study.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    ICommentService iCommentService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Comments>> findAll() {
        return new ResponseEntity<>(iCommentService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/findAllByPost/{idPost}")
    public ResponseEntity<List<Comments>> findAllByPost(@PathVariable Long idPost) {
        return new ResponseEntity<>(iCommentService.findAllByPost(idPost), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Comments> comment(@RequestBody Comments comments) {
        Comments comments1 = iCommentService.save(comments);
        return new ResponseEntity<>(comments1, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Comments> comment(@PathVariable Long id,@RequestBody Comments comments ){
        return ResponseEntity.ok(iCommentService.updateComment(comments, id));
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        iCommentService.delete(id);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Comments> findById(@PathVariable Long id) {
        Comments comments = iCommentService.findById(id).orElse(null);
        return new ResponseEntity<>(comments,HttpStatus.OK);
    }

}
