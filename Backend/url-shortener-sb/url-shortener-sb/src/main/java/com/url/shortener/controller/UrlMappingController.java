package com.url.shortener.controller;

import com.url.shortener.dtos.ClickEventDTO;
import com.url.shortener.dtos.UrlMappingDTO;
import com.url.shortener.model.ClickEvent;
import com.url.shortener.model.User;
import com.url.shortener.service.UrlMappingService;
import com.url.shortener.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    
    
    
    @GetMapping("/myurls")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<UrlMappingDTO>> getUserUrls(Principal principal){
        User user =userService.findByUsername(principal.getName());
        List<UrlMappingDTO>  urls = urlMappingService.getUrlsByUser(user);
        return ResponseEntity.ok(urls);
    }
    
    
    @GetMapping("/analytics/{shortUrl}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ClickEventDTO>> getUserUrls(@PathVariable String shortUrl,
                                                           @RequestParam("startDate") String startDate ,
                                                           @RequestParam("endDate") String endDate){
        //The ISO format looks like: "2023-12-31T23:59:59"
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime start = LocalDateTime.parse(startDate , formatter);
        LocalDateTime end = LocalDateTime.parse(endDate , formatter);
        List<ClickEventDTO> clickEventDTOS= urlMappingService.getClickEventByDate(shortUrl,start,end);
        return ResponseEntity.ok(clickEventDTOS);
        
        
    }
    
    
    
    
}
