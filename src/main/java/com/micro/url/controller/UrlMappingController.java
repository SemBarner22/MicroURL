package com.micro.url.controller;

import com.micro.url.dto.ShortenDTO;
import com.micro.url.service.UrlMappingService;
import com.micro.url.service.UrlShorteningService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class UrlMappingController {

    private final UrlMappingService urlMappingService;

    private final UrlShorteningService urlShorteningService;

    public UrlMappingController(UrlMappingService urlMappingService, UrlShorteningService urlShorteningService) {
        this.urlMappingService = urlMappingService;
        this.urlShorteningService = urlShorteningService;
    }

    @GetMapping("/v1/shortUrl")
    public String redirect(@RequestParam("url") String shortUrl, HttpServletResponse response) throws IOException {
        var urlMapping = urlMappingService.redirect(shortUrl);
        if (urlMapping == null) {
            response.sendError(400);
        } else {
            response.sendRedirect(urlMapping);
        }
        return urlMapping;
    }

    @PostMapping("/v1/data/shorten")
    public String shorten(@RequestBody ShortenDTO shortenDTO) {
        return urlShorteningService.shortenUrl(shortenDTO.getLongUrl());
    }

}
