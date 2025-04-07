package com.url.shortener.repo;

import com.url.shortener.model.ClickEvent;
import com.url.shortener.model.UrlMapping;
import com.url.shortener.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface ClickEventRepository extends JpaRepository<ClickEvent, Long> {
    
    // Get clicks only for "short.ly/abc" between Jan 1 and Jan 31
    List<ClickEvent> findByUrlMappingAndClickDateBetween(UrlMapping mapping, LocalDateTime startDate, LocalDateTime endDate);
    
    
    // Get clicks for "short.ly/abc", "short.ly/xyz", etc. between Jan 1 and Jan 31
    List<ClickEvent> findByUrlMappingInAndClickDateBetween(List<UrlMapping> urlMapping, LocalDateTime startDate, LocalDateTime endDate);
    
}
