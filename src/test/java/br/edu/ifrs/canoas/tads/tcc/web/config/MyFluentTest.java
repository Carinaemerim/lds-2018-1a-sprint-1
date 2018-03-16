package br.edu.ifrs.canoas.tads.tcc.web.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.fluentlenium.adapter.junit.FluentTest;
import org.fluentlenium.configuration.FluentConfiguration;
import org.fluentlenium.core.hook.wait.Wait;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Wait
@FluentConfiguration(webDriver="chrome")
public abstract class MyFluentTest extends FluentTest {

    @LocalServerPort
    protected int port;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }


}