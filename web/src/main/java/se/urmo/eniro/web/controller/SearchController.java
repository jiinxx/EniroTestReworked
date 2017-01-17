package se.urmo.eniro.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import se.urmo.eniro.web.adapter.SearchAdapter;
import se.urmo.eniro.web.model.SearchForm;

import javax.validation.Valid;

@Controller
public class SearchController {

    private static final String SEARCH = "search";
    private static final String SEARCHSTRING = "";
    private static final String FILTERSTRING = "";
    private static final String SEARCHRESULT = "";
    private final SearchAdapter searchAdapter;

    @Autowired
    public SearchController(final SearchAdapter searchAdapter) {
        this.searchAdapter = searchAdapter;
    }

    @RequestMapping
    public String search(Model model) {
        model.addAttribute(SEARCH, new SearchForm());
        return SEARCH;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView search(@ModelAttribute(SearchForm.ATTRIBUTE_NAME) @Valid final SearchForm searchForm, final ModelMap modelMap, final BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            modelMap.addAttribute(SEARCHSTRING, searchForm.getSearchString());
            if (!searchForm.getFilterString().isEmpty()) {
                modelMap.addAttribute(FILTERSTRING, searchForm.getFilterString());
            }
            searchAdapter.search(searchForm);
            //modelMap.addAttribute(SEARCHRESULT, searchAdapter.search(searchForm));
        }
        return new ModelAndView(SEARCH, modelMap);
    }
}
