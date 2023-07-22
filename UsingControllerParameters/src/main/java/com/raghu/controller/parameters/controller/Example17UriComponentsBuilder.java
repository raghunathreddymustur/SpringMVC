package com.raghu.controller.parameters.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Example17UriComponentsBuilder {
    // curl "http://localhost:8080/actionR?firstName=John&lastName=Doe"
    @GetMapping("/actionR")
    @ResponseBody
    public String actionR(HttpServletRequest request) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString() + "?" + request.getQueryString())
                .scheme("https")
                .path("/differentAction")
                .replaceQueryParam("lastName", "Smith")
                .build();

        return String.format(
                "Created request from original request = [%s]\n",
                uriComponents.toString()
        );
    }
}
