package se.urmo.eniro.web.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.urmo.eniro.app.service.SearchService;
import se.urmo.eniro.web.model.SearchForm;

import static org.apache.commons.lang3.Validate.notNull;

@Service
public class SearchAdapter {
    private final SearchService searchService;

    @Autowired
    public SearchAdapter(SearchService searchService) {
        this.searchService = notNull(searchService);
    }

    public void search(SearchForm searchForm) {
        System.out.println("test");
        searchService.search();
    }
}
