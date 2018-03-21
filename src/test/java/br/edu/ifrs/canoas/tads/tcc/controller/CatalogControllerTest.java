package br.edu.ifrs.canoas.tads.tcc.controller;

import br.edu.ifrs.canoas.tads.tcc.domain.Document;
import br.edu.ifrs.canoas.tads.tcc.domain.TermPaper;
import br.edu.ifrs.canoas.tads.tcc.service.CatalogService;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CatalogController.class)
public class CatalogControllerTest extends BaseControllerTest{

    // All autowired fields from Controller must have a mock
    @MockBean
    CatalogService service;

    @Test
    public void view_user_profile() throws Exception{
        this.mvc.perform(get("/catalog/")
                .with(user(userDetails))
                .accept(MediaType.TEXT_HTML)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("/document/catalog"))
        ;
    }

    @Test
    public void save_user_profile() throws Exception{
        TermPaper tp = new TermPaper();
        tp.setDocuments(Arrays.asList(new Document()));
        tp.setAdvisor(user);
        tp.setAuthor(user);

        given(this.service.search("criteria")).willReturn(Arrays.asList(tp));

        this.mvc.perform(post("/catalog/search")
                .with(user(userDetails))
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("criteria", "criteria")
        )
                .andExpect(view().name("/document/catalog :: search-results"))
                .andExpect(model().size(1))
        ;
    }
}