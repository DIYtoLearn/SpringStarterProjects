package com.DeatHertZ.urlshortener.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String checkRun()
    {
        return "Wassup Danger !";
    }

    @GetMapping("/status")
    public String getStatus(){
        return "URL Shortener is running.";
    }
}