package com.esad.emasys.services;

import com.esad.emasys.model.User;
import com.esad.emasys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public boolean isLogin(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return user.getEmail().equals(email);
        } else {
            return false;
        }
    }

    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }
}
