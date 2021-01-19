package com.interview.tbv.service;


import com.interview.tbv.model.User;
import com.interview.tbv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Bean
    private PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    public User findUserToAuthenticate(String userName) {
        return userRepository.findByEmailOrUserNameOrPhoneNumber(userName, userName, userName);
    }

    public User saveUser(User user) {
        user.setPassword(getEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

}