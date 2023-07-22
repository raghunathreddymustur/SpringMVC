package com.raghu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

import static java.util.Arrays.asList;

@Controller
public class Example06Model {

    // Visit http://localhost:8080/example06A
    @GetMapping("/example06A")
    public Model example06A() {
        Model model = new BindingAwareModelMap();
        model.addAttribute("names", asList("John", "Mary", "William", "James"));
        model.addAttribute("cities", asList("New York City", "Tokyo", "Sydney", "Bengaluru"));
        return model;
    }

    // Visit http://localhost:8080/example06B
    @GetMapping("/example06B")
    public Map example06B() {
        return Map.of(
                "names", asList("John", "Mary", "William", "James"),
                "cities", asList("New York City", "Tokyo", "Sydney", "Bengaluru")
        );
    }
}
