package com.taylorsz.shortenerurl.api;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record OriginalUrlDTO (
        @Schema(description = "Endereço original que sera encurtado", example = "https://www.pichau.com.br/computador-pichau-home-hm630-amd-ryzen-5-5600gt-16gb-ddr4-ssd-480gb-45221")
        @NotBlank(message = "original url is required")
        @Pattern(
                regexp = "^(http|https)://.*$",
                message = "A URL deve começar com http:// ou https://"
        )
        String originalUrl
){}

