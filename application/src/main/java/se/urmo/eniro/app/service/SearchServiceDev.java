package se.urmo.eniro.app.service;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class SearchServiceDev implements SearchService {

    @Value("${eniro.profile}")
    private String profile;

    @Override
    public void search(List<String> searchWord) {
        System.out.println("search dev");
        System.out.println(profile);
    }
}
