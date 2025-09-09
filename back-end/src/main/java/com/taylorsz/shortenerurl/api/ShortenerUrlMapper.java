package com.taylorsz.shortenerurl.api;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

import com.taylorsz.shortenerurl.core.UrlDomain;

@Mapper(componentModel = ComponentModel.SPRING)
public interface ShortenerUrlMapper{
    UrlDomain toUrlDomain(OriginalUrlDTO dto);

    ShortenerUrlDTO toShortenerUrlDTO(UrlDomain url);
}
