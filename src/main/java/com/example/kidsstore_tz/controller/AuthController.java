package com.example.kidsstore_tz.controller;

import com.example.kidsstore_tz.dao.AuthDao;
import com.example.kidsstore_tz.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public ResponseEntity<String> getAuth() {
        return ResponseEntity.ok("Hello World");
    }

    @PostMapping("/test-request")
    public ResponseEntity<String> testPostRequest() {
        return ResponseEntity.ok("POST request successful");
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AuthDao authDao) {
        String token = authService.signUp(authDao.getEmail(), authDao.getPassword(), authDao.getName());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody AuthDao authDao) {
        String token = authService.signIn(authDao.getEmail(), authDao.getPassword());
        return ResponseEntity.ok(token);
    }

}
