package com.practice.carselling.service;

import java.util.List;

public interface Service<T> {
    T readById(Long id);
    List<T> list(int page, int size);
    List<T> readAll();
    void deleteById(Long id);
    void save(T entity);
}
