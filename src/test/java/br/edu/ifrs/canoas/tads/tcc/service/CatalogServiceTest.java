package br.edu.ifrs.canoas.tads.tcc.service;

import br.edu.ifrs.canoas.tads.tcc.domain.TermPaper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CatalogServiceTest {

    @Autowired
    CatalogService service;

    private final String CRITERIA = "BOOT";

    @Test
    public void shouldReturnPaperByCriteria() throws Exception {
        //given
        //Existing theme 'Spring Boot'

        //when
        Iterable<TermPaper> papers = service.search(CRITERIA);

        //then
        assertThat(papers).hasSize(2)
                .extracting("theme").contains("Spring Boot");

    }

    @Test
    public void shouldNotReturnPaperByNullCriteria() throws Exception {
        //given
        //Existing theme 'Spring Boot'

        //when
        Iterable<TermPaper> papers = service.search(null);

        //then
        assertThat(papers).hasSize(0);
    }

    @Test
    public void shouldReturnAllDataWithEmptyCriteria() throws Exception {
        //given
        //Existing theme 'Spring Boot'

        //when
        Iterable<TermPaper> papers = service.search("");

        //then
        assertThat(papers).hasSize(7);
    }

}