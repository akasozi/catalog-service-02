package com.polarbookshop.catalogservice02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String getGreeting() {
        return "Welcome to the book catalog Service! It is quite the service!";
    }
}
