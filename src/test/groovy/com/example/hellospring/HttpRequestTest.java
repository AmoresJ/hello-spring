package com.example.hellospring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void canAdd() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/suma?a=1&b=2", String.class)).isEqualTo("3.0");
    }

    public int getPort(){
        return this.port;
    }

    public TestRestTemplate getRestTemplate () {
        return this.restTemplate;
    }

    void setPort(int p) {
        this.port = p;
    }

    void setRestTemplate (TestRestTemplate t) {
        this.restTemplate = t;
    }
}
