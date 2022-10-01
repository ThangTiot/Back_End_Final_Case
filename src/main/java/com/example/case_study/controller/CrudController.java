package com.example.case_study.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CrudController<O, P> {

    @PostMapping
    ResponseEntity<O> save(O object);

    @GetMapping
    ResponseEntity<List<O>> findAll();

    @GetMapping("/{id}")
    ResponseEntity<O> get(@PathVariable P id);

    @PatchMapping("/{id}")
    ResponseEntity<O> update(@PathVariable P id, O object);

    @DeleteMapping("/{id}")
    ResponseEntity<O> delete(@PathVariable P id);
}