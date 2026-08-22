package com.DeatHertZ.urlshortener.service;

/*
New original URL
       ↓
repository says “not found”
       ↓
generate an unused code
       ↓
save a new UrlMapping
       ↓
return that same code

findByOriginalUrl("https://example.com") → Optional.empty()
generate "M4pZq8Xy1"
existsByLinkKey("M4pZq8Xy1") → false
save(UrlMapping("M4pZq8Xy1", "https://example.com"))
return "M4pZq8Xy1"
*/

import com.DeatHertZ.urlshortener.entity.UrlMapping;
import com.DeatHertZ.urlshortener.repository.UrlMappingRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


public class UrlShortenerServiceSecondTest {
    private static final Logger log = LoggerFactory.getLogger(UrlShortenerServiceSecondTest.class);

    // Arrange: prepare the fake repository and its answer
    @Test
    void createsAndSavesShortCodeWhenUrlIsNew() {
        String originalUrl = "https://example.com";
        UrlMappingRepository repository = mock(UrlMappingRepository.class);
        UrlShortenerService service = new UrlShortenerService(repository);

        when(repository.findByOriginalUrl(originalUrl))
                .thenReturn(Optional.empty());

        when(repository.existsByLinkKey(anyString()))
                .thenReturn(false);
        // Optional.empty() selects the new-URL path, and existsByLinkKey(anyString()) → false lets the first generated candidate be saved.

        // Act: call createOrGetShortCode(...)
        ShortCodeResult newShortCode = service.createOrGetShortCode(originalUrl);
        log.info("{} The generated value", newShortCode.getShortCode());

        // Assert: check the returned code and repository interactions
        ArgumentCaptor<UrlMapping> mappingCaptor = ArgumentCaptor.forClass(UrlMapping.class);

        verify(repository).save(mappingCaptor.capture()); // verifies save(...) was called
        UrlMapping savedMapping = mappingCaptor.getValue(); // gives the exact UrlMapping the service attempted to persist.

        // This proves the service first looked for an existing mapping, then checked that the returned code was unused
        verify(repository).findByOriginalUrl(originalUrl);
        verify(repository).existsByLinkKey(newShortCode.getShortCode());

        // This proves input original URL == original URL persisted
        // returned random code == link key persisted
        assertEquals(originalUrl, savedMapping.getOriginalUrl());
        assertEquals(newShortCode.getShortCode(), savedMapping.getLinkKey());
        assertTrue(newShortCode.isCreated());

        // logging to check the current short code for the provided original URL
        log.info(savedMapping.getLinkKey());
        log.info(savedMapping.getOriginalUrl());
    }
}