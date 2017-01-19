package se.urmo.eniro.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import se.urmo.eniro.web.adapter.SearchAdapter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SearchControllerTest {
    private static final String INDEX = "index";
    private SearchController controller;
    private SearchAdapter searchAdapter = mock(SearchAdapter.class);
    private MvcResult mvcResult;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        controller = new SearchController(searchAdapter);

    }

    @Test
    public void index() throws Exception {
        given_a_controller();
        when_calling_index();
        then_index_view_is_returned(mvcResult);
    }


    private void then_index_view_is_returned(MvcResult mvcResult) {
        assertEquals(INDEX, mvcResult.getModelAndView().getViewName());
    }

    private void when_calling_index() throws Exception {
        mvcResult = mockMvc
                .perform(get("/").contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk()).andReturn();
    }

    private void given_a_controller() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
//
//    @Test
//    void search() throws Exception {
//        final MockMvc build = MockMvcBuilders.standaloneSetup(controller()).build();
//        MvcResult mvcResult = build.perform(post("/search")
//                .param("searchString", "pizza")
//                .param("filterString", "")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
//                .andExpect(status().isOk()).andReturn();
//
//        assertEquals(INDEX, mvcResult.getModelAndView().getViewName());
//    }
//

    // vad händer om adapterns kaster exception?
    // vad händer om formen inte validerar
    // vad händer om bindingerror
}