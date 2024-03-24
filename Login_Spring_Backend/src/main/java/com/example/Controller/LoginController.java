package com.example.Controller;


import com.example.Model.LoginModel;
import com.example.Repository.LoginRepository;
import com.example.Service.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
//@CrossOrigin(origins="http://localhost:3000/login")
public class LoginController {

    public final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/")
    public ResponseEntity<String> home() {
        String welcome = "Welcome to my Login page!";
        return ResponseEntity.ok(welcome);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody LoginModel user) {
        loginService.saveUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> finalLogin(@RequestBody LoginModel loginUser, HttpSession session) {
        LoginModel authenticatedUser = loginService.authenticate(loginUser.getUsername(), loginUser.getPassword());
        log.info("Authenticated user: " + authenticatedUser);
        if (authenticatedUser != null) {
            session.setAttribute("userId", loginUser.getUserId());
            return ResponseEntity.ok("Login successfull. Session created.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

    }

}