package com.esad.emasys.services;

import com.esad.emasys.model.User;
import com.esad.emasys.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
