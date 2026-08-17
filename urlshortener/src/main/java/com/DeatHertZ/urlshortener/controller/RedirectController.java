package com.DeatHertZ.urlshortener.controller;

import com.DeatHertZ.urlshortener.Service.UrlShortenerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.net.URI;

@RestController
public class RedirectController {

    private final UrlShortenerService urlShortenerService;
    // constructor-injection
    public RedirectController(UrlShortenerService urlShortenerService){
        this.urlShortenerService = urlShortenerService;
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortCode){

        String originalUrl = urlShortenerService.findOriginalUrl(shortCode);

        if(originalUrl == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalUrl)).build();
    }
}