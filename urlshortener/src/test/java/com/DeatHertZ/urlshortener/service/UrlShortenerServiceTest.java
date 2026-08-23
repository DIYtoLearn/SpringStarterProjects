package com.DeatHertZ.urlshortener.service;

import com.DeatHertZ.urlshortener.entity.UrlMapping;
import com.DeatHertZ.urlshortener.repository.UrlMappingRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class UrlShortenerServiceTest {
    private static final Logger log = LoggerFactory.getLogger(UrlShortenerServiceTest.class);

    @Test
        void returnsExistingShortCodeWhenUrlAlreadyExist() {
        // Arrange: prepare the fake repository and its answer
        /*
        1. Create a fake repository. In a test, we provide the fake instead of Spring providing the real repository.
        2. Create an existing UrlMapping, for example: original URL → abc123XYZ
        3. Teach the fake repository to return that mapping when asked for the original URL.
        4. Give that fake repository to a new UrlShortenerService through its const */

        String originalUrl = "https://example.com";
        String shortUrl = "http://localhost:1999/abc123XYZ";
        UrlMappingRepository repository = mock(UrlMappingRepository.class); // creates a runtime object that implements the repository interface. It has no database connection
        UrlShortenerService service = new UrlShortenerService(repository); // UrlShortenerService → Mockito fake repository
        UrlMapping existing = new UrlMapping("abc123XYZ", originalUrl, shortUrl); // tell the fake repo in advance how to respond, then check how the service used it.

        when(repository.findByOriginalUrl(originalUrl)) // Teaches the fake: “when asked about this URL, pretend the database found this mapping.”
                .thenReturn(Optional.of(existing));

    // Act: call createOrGetShortCode(...)

        ShortCodeResult result = service.createOrGetShortCode(originalUrl);
        log.info(result.getShortCode()); // Check if this returns abc123XYZ

    // Assert: check the returned code and repository interactions
        /*
        return the existing mapping’s short code // result should contain "abc123XYZ"
        do not construct a new UrlMapping
        do not call save(...)
        do not check for collisions
        do not generate a new code */

        assertEquals("abc123XYZ", result.getShortCode());
        assertFalse(result.isCreated());
        verify(repository).findByOriginalUrl(originalUrl);
        verify(repository, never()).existsByLinkKey(anyString());
        verify(repository, never()).save(any(UrlMapping.class)); // check that save was called zero times with any UrlMapping

    }
}