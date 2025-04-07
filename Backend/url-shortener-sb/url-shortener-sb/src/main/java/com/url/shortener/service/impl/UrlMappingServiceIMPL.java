package com.url.shortener.service.impl;

import com.url.shortener.dtos.ClickEventDTO;
import com.url.shortener.dtos.UrlMappingDTO;
import com.url.shortener.model.ClickEvent;
import com.url.shortener.model.UrlMapping;
import com.url.shortener.model.User;
import com.url.shortener.repo.ClickEventRepository;
import com.url.shortener.repo.UrlMappingRepository;
import com.url.shortener.service.UrlMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;


@Service
public class UrlMappingServiceIMPL implements UrlMappingService {
    
    @Autowired
    private UrlMappingRepository urlMappingRepository;
    
    @Autowired
    private ClickEventRepository clickEventRepository;
    
    @Override
    public UrlMappingDTO createShortUrl(String originalUrl, User user) {
        String shortUrl = generateUrl();
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setShortUrl(shortUrl);
        urlMapping.setUser(user);
        urlMapping.setCreatedDate(LocalDateTime.now());
        UrlMapping saveUrlMapping =  urlMappingRepository.save(urlMapping);
        
        return convertToDto(saveUrlMapping);
    }
    
 
    
    private String generateUrl() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        
        Random random = new Random();
        StringBuilder shortUrl = new StringBuilder(8);
        
        
        for (int i=0 ; i<8 ; i++){
                shortUrl.append(characters.charAt(random.nextInt(characters.length())));
        }
        return shortUrl.toString();
    }
    
    
    private UrlMappingDTO convertToDto(UrlMapping urlMapping){
        UrlMappingDTO urlMappingDTO = new UrlMappingDTO();
        urlMappingDTO.setId(urlMapping.getId());
        urlMappingDTO.setShortUrl(urlMapping.getShortUrl());
        urlMappingDTO.setOriginalUrl(urlMapping.getOriginalUrl());
        urlMappingDTO.setCreatedDate(urlMapping.getCreatedDate());
        urlMappingDTO.setUsername(urlMapping.getUser().getUsername());
        return urlMappingDTO;
    }
    
    
    
    @Override
    public List<UrlMappingDTO> getUrlsByUser(User user) {
        return urlMappingRepository.findByUser(user).stream()
                .map(this::convertToDto).toList();
    }
    
    @Override
    public List<ClickEventDTO> getClickEventByDate(String shortUrl, LocalDateTime start, LocalDateTime end) {
        UrlMapping urlMapping =urlMappingRepository.findByShortUrl(shortUrl);
        if(urlMapping != null){
           
            //Groups by date: Counts how many clicks happened on each day.
            return clickEventRepository.findByUrlMappingAndClickDateBetween(urlMapping , start,end).stream()
                    .collect(Collectors.groupingBy(click -> click.getClickDate().toLocalDate() , Collectors.counting()))
                    .entrySet().stream()
                    .map(entry -> {
                        ClickEventDTO clickEventDTO = new ClickEventDTO();
                        clickEventDTO.setClickDate(entry.getKey());
                        clickEventDTO.setCount(entry.getValue());
                        return  clickEventDTO;
                    })
                    .collect(Collectors.toList());
        }
        return null;
    }
    
    @Override
    public Map<LocalDate, Long> getTotalClicksByUserAndDate(User user, LocalDate start, LocalDate end)
    {
        List<UrlMapping> urlMappings = urlMappingRepository.findByUser(user);
        List<ClickEvent> clickEvents = clickEventRepository.findByUrlMappingInAndClickDateBetween(urlMappings,start.atStartOfDay(),end.plusDays(1).atStartOfDay());
        return clickEvents.stream()
                .collect(Collectors.groupingBy(click -> click.getClickDate().toLocalDate(),Collectors.counting()));
        
    }
}
