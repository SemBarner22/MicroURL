package com.micro.url.service;

import com.micro.url.model.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrlShorteningService {
    private List<Character> listCons = List.of('b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','y','z',
            'B','C','D','F','G','H','J','K','L','M','N','P','Q','R','S','T','V','W','X','Y','Z');
    private List<Character> listVows = List.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
    private int shortLinkSize = 6;

    @Autowired
    private Range range;

    @Autowired
    private RangeDistributionService rds;

    private Long current;

    private void getNewRange() {
        range = rds.getNewRange();
        current = range.from();
    }

    public String shortenUrl(String url) {
        if (current > range.to()) {
            getNewRange();
        }
        var count = current;
        var shortened = new StringBuilder();
        var cons = true;
        while (shortened.length() < shortLinkSize) {
            if (cons) {
                shortened.append(listCons.get((int) (count % listCons.size())));
                count /= listCons.size();
            } else {
                shortened.append(listVows.get((int) (count % listVows.size())));
                count /= listVows.size();
            }
            cons = !cons;
        }

        current++;
        return shortened.reverse().toString();
    }
}
