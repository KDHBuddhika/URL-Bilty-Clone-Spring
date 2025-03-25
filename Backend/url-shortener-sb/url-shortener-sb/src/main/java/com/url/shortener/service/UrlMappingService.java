package com.url.shortener.service;

import com.url.shortener.dtos.UrlMappingDTO;
import com.url.shortener.model.User;

public interface UrlMappingService {
    UrlMappingDTO createShortUrl(String originalUrl, User user);
}
