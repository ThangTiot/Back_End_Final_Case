package com.example.case_study.service;

import java.util.List;

public interface CrudService<O, K> {
    List<O> findAll();

    O get(K key);

    O create(O object);

    O update(O object);

    O delete(K key);
}
