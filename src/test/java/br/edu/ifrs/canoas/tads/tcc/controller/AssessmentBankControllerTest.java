package br.edu.ifrs.canoas.tads.tcc.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import br.edu.ifrs.canoas.tads.tcc.service.AssessmentBankService;
import br.edu.ifrs.canoas.tads.tcc.service.UserService;
/**
 * Created by Karen on 04/6/18.
 */

@WebMvcTest(CatalogController.class)
public class AssessmentBankControllerTest  extends BaseControllerTest{

    @MockBean
    UserService userService;
    
    @MockBean
    AssessmentBankService assessmentBankService;
    
    @Test
    public void view_user_profile() throws Exception{
        this.mvc.perform(get("/assessment-bank/")
                .with(user(userDetails))
                .accept(MediaType.TEXT_HTML)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("/assessment-bank/list"))
        ;
    }

}
