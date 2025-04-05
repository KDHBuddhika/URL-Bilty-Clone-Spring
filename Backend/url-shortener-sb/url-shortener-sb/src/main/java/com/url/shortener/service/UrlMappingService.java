package com.url.shortener.service;

import com.url.shortener.dtos.UrlMappingDTO;
import com.url.shortener.model.User;

import java.util.List;

public interface UrlMappingService {
    UrlMappingDTO createShortUrl(String originalUrl, User user);
    
    List<UrlMappingDTO> getUrlsByUser(User user);
}
