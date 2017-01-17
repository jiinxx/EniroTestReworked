package se.urmo.eniro.web.model;

import javax.validation.constraints.NotNull;

public class SearchForm {
    public final static String ATTRIBUTE_NAME = "searchForm";
    @NotNull
    private String searchString;
    @NotNull
    private String filterString;

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(final String searchString) {
        this.searchString = searchString;
    }

    public String getFilterString() {
        return filterString;
    }

    public void setFilterString(final String filterString) {
        this.filterString = filterString;
    }
}