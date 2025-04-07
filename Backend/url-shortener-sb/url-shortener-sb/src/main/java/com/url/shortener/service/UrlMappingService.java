package com.url.shortener.service;

import com.url.shortener.dtos.ClickEventDTO;
import com.url.shortener.dtos.UrlMappingDTO;
import com.url.shortener.model.UrlMapping;
import com.url.shortener.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface UrlMappingService {
    UrlMappingDTO createShortUrl(String originalUrl, User user);
    
    List<UrlMappingDTO> getUrlsByUser(User user);
    
    List<ClickEventDTO> getClickEventByDate(String shortUrl, LocalDateTime start, LocalDateTime end);
    
    Map<LocalDate, Long> getTotalClicksByUserAndDate(User user, LocalDate start, LocalDate end);
    
    UrlMapping getOriginalUrl(String shortUrl);
}
