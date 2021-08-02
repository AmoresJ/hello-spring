package com.example.hellospring

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import static org.assertj.core.api.Assertions.*

@SpringBootTest
class SmokeTest {

    @Autowired
    private HomeController homeController

    @Test
    void contextLoads(){
        assertThat(homeController).isNotNull()
    }
}
