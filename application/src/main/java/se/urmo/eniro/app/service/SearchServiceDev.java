package se.urmo.eniro.app.service;

import org.springframework.beans.factory.annotation.Value;
import se.urmo.eniro.app.client.SearchResult;

import java.util.List;
import java.util.Map;

public class SearchServiceDev implements SearchService {

    @Value("${eniro.profile}")
    private String profile;

    @Override
    public List<SearchResult> search(List<String> searchWord) {
        System.out.println("search dev");
        System.out.println(profile);
        return null;
    }

    @Override
    public Map<String, String> filteredSearch(List<String> searchWord, String filterString) {
        return null;
    }
}
