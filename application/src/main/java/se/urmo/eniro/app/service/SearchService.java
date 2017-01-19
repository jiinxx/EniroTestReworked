package se.urmo.eniro.app.service;


import se.urmo.eniro.app.client.SearchResult;

import java.util.List;
import java.util.Map;

public interface SearchService {
    List<SearchResult> search(List<String> searchWord);

    Map<String, String> filteredSearch(List<String> searchWord, String filterString);
}
