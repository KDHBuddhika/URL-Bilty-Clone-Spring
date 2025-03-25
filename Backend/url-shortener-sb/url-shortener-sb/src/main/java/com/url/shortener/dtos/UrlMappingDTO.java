package com.url.shortener.dtos;

import java.time.LocalDateTime;

public class UrlMappingDTO {
    private Long id;
    private String originalUrl;
    private  String shortUrl;
    private int clickCount;
    private LocalDateTime createdDate;
    private String username;
}
