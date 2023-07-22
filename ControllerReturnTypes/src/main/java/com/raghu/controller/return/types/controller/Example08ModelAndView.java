package com.raghu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static java.util.Arrays.asList;

@Controller
public class Example08ModelAndView {

    // Visit http://localhost:8080/example08A
    @GetMapping("/example08A")
    public ModelAndView example08A() {
        return new ModelAndView(
                "person-display",
                "person",
                new Person("John", "Doe")
        );
    }

    // Visit http://localhost:8080/example08B
    @GetMapping("/example08B")
    public ModelAndView example08B() {
        return new ModelAndView(
                "names-and-cities",
                Map.of(
                        "names", asList("John", "Mary", "William", "James"),
                        "cities", asList("New York City", "Tokyo", "Sydney", "Bengaluru")
                ),
                HttpStatus.OK
        );
    }
}
