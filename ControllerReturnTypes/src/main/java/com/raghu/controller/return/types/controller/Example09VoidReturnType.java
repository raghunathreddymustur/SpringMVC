package com.raghu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class Example09VoidReturnType {

    // curl http://localhost:8080/example09A
    @GetMapping("/example09A")
    public void example09A(ServletResponse servletResponse) throws IOException {
        servletResponse.getWriter().println("Response from example09A");
    }

    // curl http://localhost:8080/example09B
    @GetMapping("/example09B")
    public void example09B(OutputStream outputStream) throws IOException {
        outputStream.write("Response from example09B\n".getBytes());
    }

    // curl -D - http://localhost:8080/example09C
    @GetMapping("/example09C")
    @ResponseStatus(code = HttpStatus.FOUND)
    public void example09B() {
    }

    // Visit http://localhost:8080/example09D
    @GetMapping("/example09D")
    public void example09D() {
    }
}
