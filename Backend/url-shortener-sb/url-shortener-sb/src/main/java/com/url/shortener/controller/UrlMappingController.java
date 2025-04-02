package com.url.shortener.controller;

import com.url.shortener.dtos.UrlMappingDTO;
import com.url.shortener.model.User;
import com.url.shortener.service.UrlMappingService;
import com.url.shortener.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/urls")
public class UrlMappingController {
    
    @Autowired
    private UrlMappingService urlMappingService;
    
    @Autowired
    private UserService userService;
    
    //{"originalUrl":"http://example.com"}
    //http://abc.com/SJlx9jkl  --> http://example.com
    
    @PostMapping("/shorten")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UrlMappingDTO> createShortUrl(@RequestBody Map<String ,String> request,
                                                        Principal principal)
    {
          String originalUrl = request.get("originalUrl");
          User user =userService.findByUsername(principal.getName());
         UrlMappingDTO urlMappingDTO = urlMappingService.createShortUrl(originalUrl,user);
         return ResponseEntity.ok(urlMappingDTO);
        
    }
    
    
    
    @PostMapping("/myurls")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<UrlMappingDTO>> getUserUrls(Principal principal){
        User user =userService.findByUsername(principal.getName());
    }
    
    
}
