package com.DeatHertZ.urlshortener.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String createShortUrl(@RequestBody String requestBody)
    {
        System.out.println("Current State of the Maps: "+urlToShortCode+" "+shortCodeToUrl);
        return requestBody;
    }
}