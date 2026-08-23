package com.DeatHertZ.urlshortener.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "url_mapping")
public class UrlMapping {

    public UrlMapping(){
    }

    public UrlMapping(String linkKey, String originalUrl)
    {
        this.linkKey = linkKey;
        this.originalUrl = originalUrl;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "link_key", nullable = false, unique = true, length = 9)
    private String linkKey;

    @Column(name = "original_url", nullable = false, unique = true ,length = 2048)
    private String originalUrl;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    public String getLinkKey() {
        return linkKey;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }
}