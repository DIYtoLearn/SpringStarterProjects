package com.DeatHertZ.urlshortener.Service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class UrlShortenerService {

    private final Map<String, String> urlToShortCode = new HashMap<>(); // Key - Long ULR and value - shortCode
    private final Map<String, String> shortCodeToUrl = new HashMap<>(); // Key - shortCode and value - Long URL

    public String createOrGetShortCode(String originalUrl) {
        // map and generation logic

        // Long URL already present
        if(urlToShortCode.containsKey(originalUrl))
            return urlToShortCode.get(originalUrl);

        String shortCode;
        GenerateShortCode gsc = new GenerateShortCode();

        // Generate unique shortCode
        do {
            gsc.generate();
            shortCode = gsc.getShortCode().toString();
        } while (shortCodeToUrl.containsKey(shortCode));

        // Map the shortCode and Long URL to both the maps
        shortCodeToUrl.put(shortCode, originalUrl);
        urlToShortCode.put(originalUrl, shortCode);
        System.out.println("Current Map States \n"+urlToShortCode+" \n"+shortCodeToUrl); // print to check the current values
        return shortCode;
    }

    public String findOriginalUrl(String shortCode) {  // lookup logic
        // Return the Original URL for the Redirection or return Null in case no matching short code found
        return shortCodeToUrl.get(shortCode);
    }
}