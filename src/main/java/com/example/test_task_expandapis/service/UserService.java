package com.example.test_task_expandapis.service;

import com.example.test_task_expandapis.model.User;

public interface UserService {
    void addUser(User user);
    boolean existByUsername(String username);
}
