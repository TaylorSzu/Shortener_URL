package com.taylorsz.shortenerurl.core;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

public class UrlNotFoundException extends ResponseStatusException {
    public UrlNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
