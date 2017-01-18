package se.urmo.eniro.web.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.urmo.eniro.app.service.SearchService;
import se.urmo.eniro.web.model.SearchForm;

import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.Validate.notNull;

@Service
public class SearchAdapter {
    private final SearchService searchService;

    @Autowired
    public SearchAdapter(SearchService searchService) {
        this.searchService = notNull(searchService);
    }

    public void search(SearchForm searchForm) {
        System.out.println(searchForm.getSearchString());
        List<String> searchWord = Arrays.asList("pizza");
        searchService.search(searchWord);
    }
}
