package com.taylorsz.shortenerurl.persistence;

import com.taylorsz.shortenerurl.core.UrlDomain;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ShortenerUrlRepository extends JpaRepository<UrlDomain, Long> {
    boolean existsByOriginalUrl(String originalUrl);
    UrlDomain findByOriginalUrl(String originalUrl);
}