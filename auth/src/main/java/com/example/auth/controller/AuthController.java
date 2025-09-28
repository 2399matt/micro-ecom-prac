package com.example.auth.controller;

import com.example.auth.entity.CustomUser;
import com.example.auth.entity.RegistrationRequest;
import com.example.auth.exception.UsernameTakenException;
import com.example.auth.security.JwtService;
import com.example.auth.security.MUserDetails;
import com.example.auth.dao.UserRepo;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepo userRepo;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final Logger log = LoggerFactory.getLogger(AuthController.class);

    public AuthController(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request) {
        log.info("Attempting to register username: {}", request.username());
        if(userRepo.existsByUsername(request.username())){
            throw new UsernameTakenException("Username: " + request.username() + "already exists!");
        }
        CustomUser user = new CustomUser(request.username(), bCryptPasswordEncoder.encode(request.password()));
        userRepo.save(user);
        log.info("Successfully registered user: {}", request.username());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid CustomUser user) {
        log.info("Login request from {}", user.getUsername());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        if (authentication.isAuthenticated()) {
            MUserDetails userDetails = (MUserDetails) authentication.getPrincipal();
            CustomUser currUser = userDetails.getUser();
            String jwtToken = jwtService.generateToken(currUser.getUsername(), currUser.getId());
            log.info("Authentication successful for {}", currUser.getUsername());
            return ResponseEntity.ok(jwtToken);
        } else {
            log.info("Authentication failed for {}", user.getUsername());
            return ResponseEntity.notFound().build();
        }
    }
}
