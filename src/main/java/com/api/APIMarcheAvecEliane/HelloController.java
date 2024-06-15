package com.api.APIMarcheAvecEliane;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!👋";
    }
}


//=> s'affichera sur http://localhost:8080/hello