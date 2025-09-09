package com.taylorsz.shortenerurl.api;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taylorsz.shortenerurl.core.ShortenerUrlService;
import com.taylorsz.shortenerurl.core.UrlDomain;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/url")
@RequiredArgsConstructor
public class ShortenerUrlController {
    
    private final ShortenerUrlService service;
    private final ShortenerUrlMapper mapper;

    @PostMapping
    public ResponseEntity<ShortenerUrlDTO> url(@RequestBody OriginalUrlDTO dto){
        UrlDomain url = mapper.toUrlDomain(dto);
        ShortenerUrlDTO response = mapper.toShortenerUrlDTO(service.shortenerUrl(url));
        return ResponseEntity.created(null).body(response);
    }

    @GetMapping("/{shortenerUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortenerUrl){
        String originalUrl = service.redirect(shortenerUrl);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalUrl)).build();
    }
}
