package se.urmo.eniro.app.service;

import se.urmo.eniro.app.client.SearchClient;

import java.util.List;

public class SearchServiceProd implements SearchService {
    private final SearchClient searchClient;

    public SearchServiceProd(final SearchClient searchClient) {
        this.searchClient = searchClient;
    }

    @Override
    public void search(List<String> searchWord) {
        System.out.println("search prod");
        searchClient.executeCall(searchWord);
    }
}
