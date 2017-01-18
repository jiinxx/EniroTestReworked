package se.urmo.eniro.app.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.Validate.notNull;
import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

public class EniroAsyncSearchClient implements SearchClient {
    private static final String BASE_URL = "http://api.eniro.com/cs/search/basic";
    private static final String PROFILE = "profile";
    private static final String KEY = "key";
    private static final String COUNTRY = "country";
    private static final String VERSION = "version";

    private final AsyncRestTemplate asyncRestTemplate;
    @Value("${eniro.profile}")
    private String profile;

    @Value("${eniro.key}")
    private String key;

    @Value("${eniro.country}")
    private String country;

    @Value("${eniro.version}")
    private String version;

    public EniroAsyncSearchClient(final AsyncRestTemplate asyncRestTemplate) {
        this.asyncRestTemplate = notNull(asyncRestTemplate);
    }

    @Override
    public List<SearchResult> executeCall(List<String> searchwords) {

        List<ListenableFuture<ResponseEntity<SearchResult>>> responseFutures = createSearchURIList(searchwords)
                .stream()
                .map(uri -> asyncRestTemplate
                        .getForEntity(uri, SearchResult.class))
                .collect(toList());

        ArrayList<SearchResult> searchResultList = new ArrayList<>();

        for (ListenableFuture<ResponseEntity<SearchResult>> future : responseFutures) {
            try {
                ResponseEntity<SearchResult> response = future.get();
                searchResultList.add(response.getBody());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return searchResultList;
    }

    private List<URI> createSearchURIList(final List<String> searchwords) {
        return searchwords.stream().map(this::createSearchURIWithSearchWord).collect(toList());
    }

    private URI createSearchURIWithSearchWord(final String searchword) {
        return baseBuilder(profile, key, country, version)
                .queryParam("search_word", searchword)
                .build().toUri();
    }

    private static UriComponentsBuilder baseBuilder(final String profile,
                                                    final String key,
                                                    final String country,
                                                    final String version) {
        return fromHttpUrl(BASE_URL)
                .queryParam(PROFILE, profile)
                .queryParam(KEY, key)
                .queryParam(COUNTRY, country)
                .queryParam(VERSION, version);
    }


    public static void main(String[] args) {
        EniroAsyncSearchClient client = new EniroAsyncSearchClient(AsyncRestTemplateFactory.createDefaultRestTemplate());
        ArrayList<String> searchWordList = new ArrayList<>(Arrays.asList("pizza"));
        System.out.println(client.executeCall(searchWordList));
    }
}
