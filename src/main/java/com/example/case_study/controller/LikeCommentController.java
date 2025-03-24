package com.example.case_study.controller;

import com.example.case_study.model.Comments;
import com.example.case_study.model.LikeComment;
import com.example.case_study.service.ICommentService;
import com.example.case_study.service.ILikeCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like-comment")
public class LikeCommentController {
    @Autowired
    ICommentService iCommentService;
    @Autowired
    ILikeCommentService iLikeCommentService;

    @GetMapping
    public ResponseEntity<List<LikeComment>> findAll() {
        return new ResponseEntity<>(iLikeCommentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findAllByUser/{idUser}")
    public ResponseEntity<List<LikeComment>> findAllByUser(@PathVariable Long idUser) {
        return new ResponseEntity<>(iLikeCommentService.findAllByUser(idUser), HttpStatus.OK);
    }

    @PostMapping("/like")
    public ResponseEntity<LikeComment> likeComment(@RequestBody LikeComment likeComment) {
        return ResponseEntity.ok(iLikeCommentService.save(likeComment));
    }
    @DeleteMapping("/disLike/{id}")
    public void disLikeComment(@PathVariable Long id) {
        iLikeCommentService.delete(id);
    }
}
