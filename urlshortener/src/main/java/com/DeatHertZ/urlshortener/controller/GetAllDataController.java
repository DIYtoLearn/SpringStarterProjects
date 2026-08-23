package com.DeatHertZ.urlshortener.controller;

import com.DeatHertZ.urlshortener.entity.UrlMapping;
import com.DeatHertZ.urlshortener.service.UrlShortenerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class GetAllDataController {

    private final UrlShortenerService urlShortenerService;

    public GetAllDataController(UrlShortenerService urlShortenerService){
        this.urlShortenerService = urlShortenerService;
    }

    @GetMapping("/all")
    public List<UrlMapping> getAllDataController(){

        return urlShortenerService.getAllData();
    }
}