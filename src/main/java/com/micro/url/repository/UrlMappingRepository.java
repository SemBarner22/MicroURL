package com.micro.url.repository;

import com.micro.url.entity.UrlMapping;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlMappingRepository extends MongoRepository<UrlMapping, String> {
    UrlMapping findByShortUrl(String shortUrl);

}
