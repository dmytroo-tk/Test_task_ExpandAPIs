package com.example.test_task_expandapis.service.impl;

import com.example.test_task_expandapis.model.User;
import com.example.test_task_expandapis.repository.UserRepository;
import com.example.test_task_expandapis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUsersByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User: '%s' not found", username)));
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }
}
