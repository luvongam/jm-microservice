package com.jamlech.demo1.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}

