package com.micro.url.service;

import com.micro.url.repository.UrlMappingRepository;
import org.springframework.stereotype.Service;

@Service
public class UrlMappingService {

    private final UrlMappingRepository urlMappingRepository;

    public UrlMappingService(UrlMappingRepository urlMappingRepository) {
        this.urlMappingRepository = urlMappingRepository;
    }

    public String redirect(String shortUrl) {
        return urlMappingRepository.findByShortUrl(shortUrl).getLongUrl();
    }

}
