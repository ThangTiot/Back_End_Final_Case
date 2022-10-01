package com.example.case_study.controller;

import com.example.case_study.controller.dto.CommentDto;
import com.example.case_study.model.Comment;
import com.example.case_study.repository.ICommentRepository;
import com.example.case_study.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/comments")
public class CommentController implements CrudController<CommentDto, Long> {
    @Autowired
    CommentService iCommentService;

    @Override
    public ResponseEntity<CommentDto> save(CommentDto object) {
        return ResponseEntity.ok(iCommentService.create(object));
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> findAll() {
        return ResponseEntity.ok(iCommentService.findAll());
    }

    @Override
    public ResponseEntity<CommentDto> get(Long id) {
        return ResponseEntity.ok(iCommentService.get(id));
    }

    @Override
    public ResponseEntity<CommentDto> update(Long id, CommentDto object) {
        object.setId(id);
        return ResponseEntity.ok(iCommentService.update(object));
    }

    @Override
    public ResponseEntity<CommentDto> delete(Long id) {
        return ResponseEntity.ok(iCommentService.delete(id));
    }


//    @PostMapping("/create")
//    public ResponseEntity<Comment> comment(@RequestBody Comment comments) {
//        Comment comments1 = iCommentService.save(comments);
//        return new ResponseEntity<>(comments1, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/delete/{id}")
//    public void delete(@PathVariable Long id) {
//        Optional<Comment> comments = iCommentService.get(id);
//        if (comments.isPresent()) {
//            comments.get().setIsDeleted(false);
//            iCommentService.save(comments.get());
//        }
//    }
}
