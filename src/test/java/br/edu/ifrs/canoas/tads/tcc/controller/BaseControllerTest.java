package br.edu.ifrs.canoas.tads.tcc.controller;


import br.edu.ifrs.canoas.tads.tcc.config.auth.UserImpl;
import br.edu.ifrs.canoas.tads.tcc.domain.User;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
public abstract class BaseControllerTest {

    protected static final Long   USER_ID= 101L;
    protected static final String USER_NAME= "User Principal";
    protected static final String FIELD_SAVED = "Salvo com sucesso";

    @Autowired
    MockMvc mvc;

    User user;
    UserImpl userDetails;

    @Before
    public void setup() {
        user = new User();
        user.setId(USER_ID);
        user.setName(USER_NAME);
        userDetails = new UserImpl("user", "user",  AuthorityUtils.createAuthorityList("ROLE_USER"), user);

        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(securityContext.getAuthentication().getPrincipal()).thenReturn(userDetails);
        SecurityContextHolder.setContext(securityContext);
    }


}