package com.url.shortener.service;

import com.url.shortener.dtos.JwtAuthenticationResponse;
import com.url.shortener.dtos.LoginRequest;
import com.url.shortener.model.User;

public interface UserService {
    
    User registerUser(User user);
    
    JwtAuthenticationResponse authenticationUser(LoginRequest loginRequest);
    
    User findByUsername(String name);
}
