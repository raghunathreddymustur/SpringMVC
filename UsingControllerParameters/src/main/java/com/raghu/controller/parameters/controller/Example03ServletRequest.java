package com.raghu.controller.parameters.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;

import static java.lang.String.format;

@Controller
public class Example03ServletRequest {
    // curl localhost:8080/actionC
    @GetMapping("/actionC")
    @ResponseBody
    public String actionC(ServletRequest servletRequest) {
        return format("Retrieved request on server = [%s:%d]\n", servletRequest.getServerName(), servletRequest.getServerPort());
    }
}
