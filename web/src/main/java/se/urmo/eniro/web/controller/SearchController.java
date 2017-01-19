package se.urmo.eniro.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import se.urmo.eniro.web.adapter.SearchAdapter;
import se.urmo.eniro.web.model.SearchForm;

import javax.validation.Valid;

@Controller
public class SearchController {

    private static final String INDEX = "index";
    private static final String SEARCHSTRING = "searchstring";
    private static final String SEARCHRESULT = "searchresult";
    private static final String FILTERSTRING = "filterstring";
    private final SearchAdapter searchAdapter;

    @Autowired
    public SearchController(final SearchAdapter searchAdapter) {
        this.searchAdapter = searchAdapter;
    }

    @RequestMapping
    public String index(Model model) {
        model.addAttribute(new SearchForm());
        return INDEX;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String search(@ModelAttribute(SearchForm.ATTRIBUTE_NAME) @Valid final SearchForm searchForm, final Model model, final BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            model.addAttribute(SEARCHRESULT, searchAdapter.search(searchForm));
        }
        return INDEX;
    }
}
