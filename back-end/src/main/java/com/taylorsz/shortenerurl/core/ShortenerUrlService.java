package com.taylorsz.shortenerurl.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.taylorsz.shortenerurl.persistence.ShortenerUrlRepository;

@Service
@RequiredArgsConstructor
public class ShortenerUrlService {
    private final ShortenerUrlRepository shortenerUrlRepository;
    private final Base64 base64;

    public UrlDomain shortenerUrl(UrlDomain url) {
        if (shortenerUrlRepository.existsByOriginalUrl(url.getOriginalUrl())) {
            return shortenerUrlRepository.findByOriginalUrl(url.getOriginalUrl());
        }
        else{
            UrlDomain savedUrl = shortenerUrlRepository.save(url);
            return shortenerUrlRepository.save(savedUrl);
        }
    }

    public String redirect(String url) {
        Long id = base64.decode(url);
        UrlDomain findUrl = findById(id);
        return findUrl.getOriginalUrl();
    }

    public UrlDomain findById(Long id){
        return shortenerUrlRepository.findById(id).orElseThrow(() -> new UrlNotFoundException("URL not found"));
    }

}
