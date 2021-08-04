package com.example.hellospring

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort

import static org.assertj.core.api.Assertions.assertThat

@SpringBootTest
class HelloSpringApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + this.port + "/hola", String.class)).isEqualTo("Hola holita pruebecita");

    }
}
