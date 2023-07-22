package com.raghu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.OutputStream;

@Controller
public class Example14StreamingResponseBody {

    // curl http://localhost:8080/example14
    @GetMapping("/example14")
    public StreamingResponseBody example14() {
        return new StreamingResponseBody() {
            @Override
            public void writeTo(OutputStream outputStream) throws IOException {
                outputStream.write("John".getBytes());
                outputStream.write("Doe".getBytes());
            }
        };
    }
}
