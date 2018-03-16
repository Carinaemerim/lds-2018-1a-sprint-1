package br.edu.ifrs.canoas.tads.tcc.service;

import br.edu.ifrs.canoas.tads.tcc.domain.TermPaper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.fluentlenium.assertj.FluentLeniumAssertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CatalogServiceTest {

    @Autowired
    CatalogService service;

    private final String CRITERIA = "BOOT";

    @Test
    public void given_existingDBData_when_searchingByBOOT_then_returnSpringBootPaper() throws Exception {
        //given
        //Existing theme 'Spring Boot'

        //when
        Iterable<TermPaper> papers = service.search(CRITERIA);

        //then
        assertThat(papers).hasSize(1)
                .extracting("theme").contains("Spring Boot");

    }

    @Test
    public void given_existingDBData_when_searchingWithNull_then_returnEmptyList() throws Exception {
        //given
        //Existing theme 'Spring Boot'

        //when
        Iterable<TermPaper> papers = service.search(null);

        //then
        assertThat(papers).hasSize(0);
    }

    @Test
    public void given_existingDBData_when_searchingEmptyString_then_returnAllData() throws Exception {
        //given
        //Existing theme 'Spring Boot'

        //when
        Iterable<TermPaper> papers = service.search("");

        //then
        assertThat(papers).hasSize(2);
    }

}