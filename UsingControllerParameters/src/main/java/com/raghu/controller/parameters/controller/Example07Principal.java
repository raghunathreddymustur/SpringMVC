package com.raghu.controller.parameters.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

import static java.lang.String.format;

@Controller
public class Example07Principal {
    // Visit http://localhost:8080/actionG in your browser
    // use john/john or mary/mary as username and password
    @GetMapping("/actionG")
    @ResponseBody
    public String actionG(Principal principal) {
        return format("Authenticated with principal = [%s]\n", principal.getName());
    }
}
