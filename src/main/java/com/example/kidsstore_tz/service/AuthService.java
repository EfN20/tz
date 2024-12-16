package com.example.kidsstore_tz.service;

import com.example.kidsstore_tz.config.JwtUtil;
import com.example.kidsstore_tz.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String signUp(String email, String password, String name) {
        User user = userService.saveUser(new User(email, passwordEncoder.encode(password), name));

        return jwtUtil.generateToken(user);
    }

    public String signIn(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                email,
                password
        ));

        var user = userService.loadUserByUsername(email);

        return jwtUtil.generateToken(user);
    }

}
