package se.urmo.eniro.app.client;

import java.util.List;

public interface SearchClient {
    List<SearchResult> executeCall(List<String> searchwords);
}
