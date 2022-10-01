package com.example.case_study.service.imp;

import com.example.case_study.controller.dto.CommentDto;
import com.example.case_study.model.Comment;
import com.example.case_study.repository.ICommentRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements com.example.case_study.service.CommentService {
    @Autowired
    private ICommentRepository repository;

    @Override
    public List<CommentDto> findAll() {
        return repository.findAll().stream().map(CommentDto::new).collect(Collectors.toList());
    }

    @Override
    public CommentDto create(CommentDto object) {
        Comment comment = new Comment(object);
        return new CommentDto(repository.save(comment));
    }

    @Override
    public CommentDto update(CommentDto object) {
        Comment comment = findById(object.getId());
        comment.update(object);
        comment = repository.save(comment);
        return new CommentDto(comment);
    }

    @Override
    public CommentDto delete(Long key) {
        Comment comment = findById(key);
        comment.setIsDeleted(true);
        return new CommentDto(comment);
    }

    @Override
    public CommentDto get(Long id) {
        return new CommentDto(findById(id));
    }

    @SneakyThrows
    private Comment findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Comment not found"));
    }
}
