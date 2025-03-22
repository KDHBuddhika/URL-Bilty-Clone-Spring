package com.url.shortener.service;

import com.url.shortener.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String email;

    private  String password;

    private Collection<? extends GrantedAuthority> authorities;


    //This method converts a User object (your application's user model) into a UserDetailsImpl object
    public static UserDetailsImpl build(User user){
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(authority)
        );
    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
