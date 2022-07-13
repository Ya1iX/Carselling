package com.practice.carselling.service;

import com.practice.carselling.entity.User;

import java.util.List;

public interface UserService {
    List<User> readAll();
    void registerUser(User user);
}
