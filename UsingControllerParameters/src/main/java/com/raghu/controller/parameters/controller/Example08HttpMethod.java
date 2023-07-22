package com.raghu.controller.parameters.controller;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static java.lang.String.format;

@Controller
public class Example08HttpMethod {
    // curl -X GET localhost:8080/actionH
    // curl -X POST localhost:8080/actionH
    // curl -X PUT localhost:8080/actionH
    @RequestMapping("/actionH")
    @ResponseBody
    public String actionH(HttpMethod httpMethod) {
        return format("HTTP Method = [%s]\n", httpMethod);
    }
}
