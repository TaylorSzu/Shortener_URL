package com.taylorsz.shortenerurl.api;

import java.net.URI;

import com.taylorsz.shortenerurl.core.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
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
    @Operation(
            summary = "Encurta URL",
            description = "Recebe uma URL original válida e retorna a versão encurtada."
    )
    @ApiResponse(
            responseCode = "201",
            description = "URL encurtada retornada com sucesso",
            content = @Content(schema = @Schema(implementation = ShortenerUrlDTO.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Erro de validação nos dados de entrada",
            content = @Content(schema = @Schema(implementation = ApiError.class))
    )
    @ApiResponse(
            responseCode = "500",
            description = "Erro interno inesperado",
            content = @Content(schema = @Schema(implementation = ApiError.class))
    )
    public ResponseEntity<ShortenerUrlDTO> url(@Valid @RequestBody OriginalUrlDTO dto){
        UrlDomain url = mapper.toUrlDomain(dto);
        ShortenerUrlDTO response = mapper.toShortenerUrlDTO(service.shortenerUrl(url));
        return ResponseEntity.created(null).body(response);
    }

    @GetMapping("/{shortenerUrl}")
    @Operation(
            summary = "Redireciona para a URL original",
            description = "Recebe o código encurtado e redireciona para a URL original."
    )
    @ApiResponse(
            responseCode = "302",
            description = "Redirecionamento para a URL original"
    )
    @ApiResponse(
            responseCode = "404",
            description = "URL encurtada não encontrada",
            content = @Content(schema = @Schema(implementation = ApiError.class))
    )
    @ApiResponse(
            responseCode = "500",
            description = "Erro interno inesperado",
            content = @Content(schema = @Schema(implementation = ApiError.class))
    )
    public ResponseEntity<Void> redirect(
            @Parameter(description = "Codigo da URL encurtada", example = "dnh")
            @Valid
            @PathVariable String shortenerUrl){
        String originalUrl = service.redirect(shortenerUrl);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalUrl)).build();
    }
}
