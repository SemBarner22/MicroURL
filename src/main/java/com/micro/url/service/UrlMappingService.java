package com.micro.url.service;

import com.micro.url.entity.UrlMapping;
import com.micro.url.repository.UrlMappingRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UrlMappingService {

    private final UrlMappingRepository urlMappingRepository;

    public UrlMappingService(UrlMappingRepository urlMappingRepository) {
        this.urlMappingRepository = urlMappingRepository;
    }

    public String redirect(String shortUrl) {
        return urlMappingRepository.findByShortUrl(shortUrl).getLongUrl();
    }

    public String shorten(String longUrl) {
        var mapping = new UrlMapping();
        var shortUrl = UUID.randomUUID().toString().substring(0, 10);
        mapping.setLongUrl(longUrl);
        mapping.setShortUrl(shortUrl);
        mapping.setId(UUID.randomUUID().toString());
        urlMappingRepository.save(mapping);
        return shortUrl;
    }
}
