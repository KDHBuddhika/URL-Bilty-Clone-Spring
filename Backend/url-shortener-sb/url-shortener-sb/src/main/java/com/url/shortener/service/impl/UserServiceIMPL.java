package com.url.shortener.service.impl;

import com.url.shortener.dtos.JwtAuthenticationResponse;
import com.url.shortener.dtos.LoginRequest;
import com.url.shortener.model.User;
import com.url.shortener.repo.UserRepository;
import com.url.shortener.security.jwt.JwtUtils;
import com.url.shortener.service.UserDetailsImpl;
import com.url.shortener.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserServiceIMPL implements UserService {
    
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserRepository userRepository;
    
   
    private AuthenticationManager authenticationManager;
    
    private JwtUtils jwtUtils;
    
    @Override
    public User registerUser(User user) {
      
            if (user.getPassword() == null) {
                throw new IllegalArgumentException("Password cannot be null");
            }
     
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
        
    }
    
    @Override
    public JwtAuthenticationResponse authenticationUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(userDetails);
        return new JwtAuthenticationResponse(jwt);
    }
    
    
    
    @Override
    public User findByUsername(String name) {
        return userRepository.findByUsername(name).orElseThrow(
                () -> new UsernameNotFoundException("UserNotFound"));
    }
    
    
    
    
}
