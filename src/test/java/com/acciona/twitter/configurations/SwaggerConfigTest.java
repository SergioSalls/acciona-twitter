package com.acciona.twitter.configurations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class SwaggerConfigTest {

    @Autowired
    private SwaggerConfig swaggerConfig;

    @Test
    void buildDocket() {
        Docket docket = swaggerConfig.buildDocket();
        assertNotNull(docket);
        assertEquals(docket.getDocumentationType(), DocumentationType.SWAGGER_2);
    }
}