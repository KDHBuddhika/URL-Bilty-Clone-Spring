package com.url.shortener.service;

import com.url.shortener.dtos.ClickEventDTO;
import com.url.shortener.dtos.UrlMappingDTO;
import com.url.shortener.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UrlMappingService {
    UrlMappingDTO createShortUrl(String originalUrl, User user);
    
    List<UrlMappingDTO> getUrlsByUser(User user);
    
    List<ClickEventDTO> getClickEventByDate(String shortUrl, LocalDateTime start, LocalDateTime end);
}
