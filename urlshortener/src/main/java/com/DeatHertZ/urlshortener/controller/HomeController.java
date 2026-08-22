package com.DeatHertZ.urlshortener.controller;

import com.DeatHertZ.urlshortener.dto.CreateUrlRequest;
import com.DeatHertZ.urlshortener.service.ShortCodeResult;
import com.DeatHertZ.urlshortener.service.UrlShortenerService;
import com.DeatHertZ.urlshortener.validation.UrlValidator;
import com.DeatHertZ.urlshortener.dto.CreateUrlResponse;
import com.DeatHertZ.urlshortener.exception.InvalidUrlException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class HomeController {

    private final UrlShortenerService urlShortenerService;

    // constructor-injection
    public HomeController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @GetMapping()
    public String checkRun() {
        return "Wassup Danger !";
    }

    @GetMapping("/status")
    public String getStatus() {
        return "URL Shortener is running.";
    }

    @PostMapping(value = "/urls", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateUrlResponse> createShortUrl(@RequestBody CreateUrlRequest request) {
        String originalUrl = request.getUrl(); // Extract only the long URL from the JSON body, file in service layer
        // For {}, Spring binds request.getUrl() to null. And controller calls: urlShortenerService.createOrGetShortCode(null)
        // Can result in null pointer Exception

        // invalid/missing URL → 400 → service not called → no database row
        // valid URL           → service → MySQL → DTO response
        if (!UrlValidator.isValidUrl(originalUrl))
            throw new InvalidUrlException("Not a valid URL"); // invalid URL → InvalidUrlException → GlobalExceptionHandler → 400 + ApiErrorResponse JSON

        ShortCodeResult result = urlShortenerService.createOrGetShortCode(originalUrl);
        String shortCode = result.getShortCode();
        String shortUrl = "http://localhost:1999/" + shortCode;
        // Check if Long URL exists ? return existing short code
        // Done in service layer
        // Else Generate short code and return the new URL in response
        // Done in service layer
        //return "http://localhost:1999/"+urlShortenerService.createOrGetShortCode(originalUrl); // Older way of sending the response for a post request with the long URL
        // In the older way the entities fields are exposed to the controller that is not always required
        //return new CreateUrlResponse(shortCode, shortUrl, originalUrl); // @RestController causes Spring to serialize this returned object as JSON.

        CreateUrlResponse response = new CreateUrlResponse(shortCode, shortUrl, originalUrl);

        if(result.isCreated()) {
            return ResponseEntity.created(URI.create(shortUrl)).body(response);}

        return ResponseEntity.ok(response);
    }
}