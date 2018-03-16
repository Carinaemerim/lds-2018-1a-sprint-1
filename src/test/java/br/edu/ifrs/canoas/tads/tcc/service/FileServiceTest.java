package br.edu.ifrs.canoas.tads.tcc.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class FileServiceTest {

    @Autowired
    FileService service;

    private final Long ID = 0L;

    @Test
    public void given_existingDBData_when_searchingByBOOT_then_returnSpringBootPaper() throws Exception {
        //given
        //Existing theme 'Spring Boot'

        //when
        HttpEntity<byte[]> file = service.download(ID);

        //then
        assertThat(file.hasBody()).isTrue();
        assertThat(file.getHeaders().getContentType().toString()).isEqualTo("image/jpeg");
    }

    @Test
    public void given_existingDBData_when_searchingWithNull_then_returnEmptyList() throws Exception {
        //given
        //Existing theme 'SpringBoot'

        //when
        HttpEntity<byte[]> file = service.download(null);

        //then
        assertThat(file).isNull();
    }



}