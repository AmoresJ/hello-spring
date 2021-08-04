package com.example.hellospring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/hola")
    public @ResponseBody String hola() {
        return "Hola holita pruebecita";
    }
}
