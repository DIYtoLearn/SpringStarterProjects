package com.DeatHertZ.urlshortener.controller;

import com.DeatHertZ.urlshortener.Service.CreateUrlRequest;
import com.DeatHertZ.urlshortener.Service.UrlShortenerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HomeController {

    private final UrlShortenerService urlShortenerService;
    // constructor-injection
    public HomeController(UrlShortenerService urlShortenerService){
        this.urlShortenerService = urlShortenerService;
    }

    @GetMapping()
    public String checkRun()
    {
        return "Wassup Danger !";
    }

    @GetMapping("/status")
    public String getStatus(){
        return "URL Shortener is running.";
    }

    @PostMapping(value = "/urls", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createShortUrl(@RequestBody CreateUrlRequest request)
    {
        String originalUrl = request.getUrl(); // Extract only the long URL from the JSON body, file in service layer

        // Check if Long URL exists ? return existing short code
        // Done in Service layer
        // Else Generate short code and return the new URL in response
        // Done in Service layer
            return "http://localhost:1999/"+urlShortenerService.createOrGetShortCode(originalUrl);
        }
}