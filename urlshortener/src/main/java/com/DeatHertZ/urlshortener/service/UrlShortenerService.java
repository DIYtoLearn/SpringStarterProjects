package com.DeatHertZ.urlshortener.service;

import com.DeatHertZ.urlshortener.entity.UrlMapping;
import com.DeatHertZ.urlshortener.repository.UrlMappingRepository;
import org.springframework.stereotype.Service;
//import java.util.HashMap;
//import java.util.Map;
import java.util.Optional;

@Service
public class UrlShortenerService {

    private final UrlMappingRepository urlMappingRepository;
    public UrlShortenerService(UrlMappingRepository urlMappingRepository)
    {
        this.urlMappingRepository = urlMappingRepository;
    }

//    private final Map<String, String> urlToShortCode = new HashMap<>(); // Key - Long ULR and value - shortCode
//    private final Map<String, String> shortCodeToUrl = new HashMap<>(); // Key - shortCode and value - Long URL

    public ShortCodeResult createOrGetShortCode(String originalUrl) {   // Map Store and generation logic

        // Long URL already present
//        if(urlToShortCode.containsKey(originalUrl)) // Old Logic for Long URL exist check !
//            return urlToShortCode.get(originalUrl);

        Optional<UrlMapping> existingMapping = urlMappingRepository.findByOriginalUrl(originalUrl);
        if(existingMapping.isPresent()){
            return new ShortCodeResult(existingMapping.get().getLinkKey(), false);
        }

        String shortCode;
        GenerateShortCode gsc = new GenerateShortCode();

        // Generate unique shortCode
        do {
            gsc.generate();
            shortCode = gsc.getShortCode().toString();
        } while (urlMappingRepository.existsByLinkKey(shortCode));

        // Map the shortCode and Long URL to both the maps // THE OLDER LOGIC FOR IN MEMORY MAPS STORING THE DATA
//        shortCodeToUrl.put(shortCode, originalUrl);
//        urlToShortCode.put(originalUrl, shortCode);
//        System.out.println("Current Map States \n"+urlToShortCode+" \n"+shortCodeToUrl); // print to check the current values

        UrlMapping urlMapping = new UrlMapping(shortCode, originalUrl);
        urlMappingRepository.save(urlMapping); // The database will generate id and created_at;
        // the service only needs to return shortCode.

        return new ShortCodeResult(shortCode, true);
    }

    public String findOriginalUrl(String shortCode) {  // lookup logic
        // Return the Original URL for the Redirection or return Null in case no matching short code found
        return urlMappingRepository.findByLinkKey(shortCode)
                .map(UrlMapping::getOriginalUrl)
                .orElse(null);
    }
}