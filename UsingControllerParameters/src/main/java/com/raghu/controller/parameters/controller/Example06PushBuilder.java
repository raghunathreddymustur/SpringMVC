package com.raghu.controller.parameters.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.PushBuilder;

@Controller
public class Example06PushBuilder {
    // Visit http://localhost:8080/actionF/no-push in your browser
    @GetMapping("/actionF/no-push")
    public String actionFNoPush() {
        return "images";
    }

    // Visit https://localhost:8443/actionF/with-push in your browser
    @GetMapping("/actionF/with-push")
    public String actionFWithPush(PushBuilder pushBuilder) {
        if (pushBuilder != null) {
            pushBuilder.path("/img/b-module02.jpg").push();
            pushBuilder.path("/img/b-module05.jpg").push();
            pushBuilder.path("/img/b-module08.jpg").push();
        }

        return "images";
    }
}
