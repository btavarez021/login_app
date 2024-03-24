package com.example.Service;

import com.example.Model.LoginModel;
import com.example.Repository.LoginRepository;
import lombok.extern.slf4j.Slf4j;
import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@Slf4j
public class LoginService {
    private final LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public com.example.Model.LoginModel authenticate(String username, String password) {
        LoginModel user = loginRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            // Check if the provided password matches the stored password
            return user;
        }
        log.info(username + " not found or incorrect password.");
        return null; // User not found or incorrect password
    }

    public void saveUser(LoginModel user) {
        log.info(user + " has been saved to the database");
        loginRepository.save(user);
    }
}
