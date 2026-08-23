package com.DeatHertZ.urlshortener.repository;

import com.DeatHertZ.urlshortener.entity.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {

    Optional<UrlMapping> findByOriginalUrl(String originalUrl);
    Optional<UrlMapping> findByLinkKey(String linkKey);

    boolean existsByLinkKey(String linkKey);

}