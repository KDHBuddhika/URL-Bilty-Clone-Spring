package com.url.shortener.service.impl;

import com.url.shortener.dtos.UrlMappingDTO;
import com.url.shortener.model.User;
import com.url.shortener.service.UrlMappingService;
import org.springframework.stereotype.Service;


@Service
public class UrlMappingServiceIMPL implements UrlMappingService {
    
    
    @Override
    public UrlMappingDTO createShortUrl(String originalUrl, User user) {
        return null;
    }
}
