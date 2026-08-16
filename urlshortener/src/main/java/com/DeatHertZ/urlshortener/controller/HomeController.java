package com.DeatHertZ.urlshortener.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HomeController {

    private final Map<String, String> urlToShortCode = new HashMap<>();
    private final Map<String, String> shortCodeToUrl = new HashMap<>();


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

        String shortCode;
        String originalUrl = request.getUrl(); // Extract only the long URL from the json body

        // Check if short Long URL exists ? return existing short code
        if(urlToShortCode.containsKey(originalUrl)) { // Check if Long URL is already present
            System.out.println("Current State of the Maps: "+urlToShortCode+" "+shortCodeToUrl);
            return "http://localhost:1999/api/"+urlToShortCode.get(originalUrl);
        }

        // Else Generate short code and return the new URL in response
        else{
            GenerateShortCode gsc = new GenerateShortCode();

            do {
                gsc.generate();
                shortCode = gsc.getShortCode().toString();
            } while (shortCodeToUrl.containsKey(shortCode));

            shortCodeToUrl.put(shortCode, originalUrl);
            urlToShortCode.put(originalUrl, shortCode);

            System.out.println("Current State of the Maps: "+urlToShortCode+" "+shortCodeToUrl);
            return "http://localhost:1999/api/"+shortCode;

        }
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortCode){

        String originalURL = shortCodeToUrl.get(shortCode);

        if(originalURL == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalURL)).build();

    }
}