package com.raghu.controller.parameters.controller;

import com.spring.professional.exam.tutorial.module05.question09.ds.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Example18AnyType {
    // curl "http://localhost:8080/actionS?firstName=John&lastName=Doe"
    @GetMapping("/actionS")
    @ResponseBody
    public String actionS(String firstName, String lastName) {
        return String.format(
                "firstName = [%s], lastName = [%s]\n",
                firstName, lastName
        );
    }

    // Visit http://localhost:8080/actionS/simpleForm in your browser
    @GetMapping("/actionS/simpleForm")
    public String actionSSimpleForm(Person person) {
        return "person-form-simple";
    }

    @PostMapping("/actionS/simpleForm")
    public String actionSSimpleFormSubmit(Person person) {
        return "person-display-simple";
    }
}
