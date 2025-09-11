package com.taylorsz.shortenerurl.api;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ShortenerUrlDTO(
        @Schema(description = "Endereço ja encurtado para uso", example = "https://shortenerurl-8bfw.onrender.com/url/h")
        @NotBlank(message = "encode is required")
        String shortenerUrl
) {
}
