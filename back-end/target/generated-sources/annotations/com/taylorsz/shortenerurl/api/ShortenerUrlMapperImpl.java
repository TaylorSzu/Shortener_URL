package com.taylorsz.shortenerurl.api;

import com.taylorsz.shortenerurl.core.UrlDomain;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-09T15:49:16-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.2 (Oracle Corporation)"
)
@Component
public class ShortenerUrlMapperImpl implements ShortenerUrlMapper {

    @Override
    public UrlDomain toUrlDomain(OriginalUrlDTO dto) {
        if ( dto == null ) {
            return null;
        }

        UrlDomain.UrlDomainBuilder urlDomain = UrlDomain.builder();

        urlDomain.originalUrl( dto.originalUrl() );

        return urlDomain.build();
    }

    @Override
    public ShortenerUrlDTO toShortenerUrlDTO(UrlDomain url) {
        if ( url == null ) {
            return null;
        }

        String shortenerUrl = null;

        shortenerUrl = url.getShortenerUrl();

        ShortenerUrlDTO shortenerUrlDTO = new ShortenerUrlDTO( shortenerUrl );

        return shortenerUrlDTO;
    }
}
