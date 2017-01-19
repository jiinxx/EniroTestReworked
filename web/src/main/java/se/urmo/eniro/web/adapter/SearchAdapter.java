package se.urmo.eniro.web.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.urmo.eniro.app.service.SearchService;
import se.urmo.eniro.web.model.SearchForm;

import java.util.Arrays;
import java.util.Map;

import static org.apache.commons.lang3.Validate.notNull;

@Service
public class SearchAdapter {
    private static final String SEPARATOR = ",";
    private final SearchService searchService;

    @Autowired
    public SearchAdapter(SearchService searchService) {
        this.searchService = notNull(searchService);
    }

    public Map<String, String> search(SearchForm searchForm) {
        notNull(searchForm);
        notNull(searchForm.getFilterString());
        notNull(searchForm.getSearchString());

        String[] searchWords = searchForm.getSearchString().trim().split(SEPARATOR);
        return searchService.filteredSearch(Arrays.asList(searchWords), searchForm.getFilterString());
    }
}
