package se.urmo.eniro.app.service;

import se.urmo.eniro.app.client.SearchClient;
import se.urmo.eniro.app.client.SearchResult;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toMap;

public class SearchServiceProd implements SearchService {
    private final SearchClient searchClient;

    public SearchServiceProd(final SearchClient searchClient) {
        this.searchClient = searchClient;
    }

    @Override
    public List<SearchResult> search(List<String> searchWord) {
        return searchClient.executeCall(searchWord);

    }

    @Override
    public Map<String, String> filteredSearch(final List<String> searchWord, final String filterString) {
        List<SearchResult> searchResults = search(searchWord);
        return searchResults.stream()
                .flatMap(sr -> sr.adverts().stream())
                .filter(advert -> matchesFilter(filterString, advert))
                .collect(toMap(a -> a.companyInfo().companyName(),
                        a -> a.infoPageLink(), (a, b) -> a));
    }

    private boolean matchesFilter(String filterString, SearchResult.Adverts advert) {
        Pattern pattern = Pattern.compile(filterString);
        return pattern.matcher(advert.companyInfo().companyName()).find();
    }
}
