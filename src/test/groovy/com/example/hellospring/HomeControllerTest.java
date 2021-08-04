package com.example.hellospring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HomeControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void hola() {
        assertThat(this.testRestTemplate.getForObject("http://localhost:" + this.port + "/hola", String.class)).isEqualTo("Hola holita pruebecita");
    }

    public int getPort() {
        return this.port;
    }

    public TestRestTemplate getTestRestTemplate() {
        return this.testRestTemplate;
    }

    void setPort(int p) {
        this.port = p;
    }

    void setTestRestTemplate(TestRestTemplate t) {
        this.testRestTemplate = t;
    }
}