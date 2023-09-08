package com.micro.url.controller;

import com.micro.url.dto.ShortenDTO;
import com.micro.url.service.UrlMappingService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlMappingController {

    private final UrlMappingService urlMappingService;

    public UrlMappingController(UrlMappingService urlMappingService) {
        this.urlMappingService = urlMappingService;
    }

    @GetMapping("/v1/shortUrl")
    public String redirect(@RequestParam("url") String shortUrl) {
        return urlMappingService.redirect(shortUrl);
    }

    @PostMapping("/v1/data/shorten")
    public String shorten(@RequestBody ShortenDTO shortenDTO) {
        return urlMappingService.shorten(shortenDTO.getLongUrl());
    }



}
