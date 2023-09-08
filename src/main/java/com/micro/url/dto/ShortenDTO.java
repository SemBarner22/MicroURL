package com.micro.url.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShortenDTO {

    @JsonProperty("long_url")
    private String longUrl;

}
