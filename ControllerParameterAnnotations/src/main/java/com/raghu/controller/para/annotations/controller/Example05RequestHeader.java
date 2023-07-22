package com.raghu.controller.para.annotations.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Optional;

@Controller
public class Example05RequestHeader {
    // curl -H "Accept-Version: v2.1" http://localhost:8080/example05A
    @GetMapping("/example05A")
    @ResponseBody
    public String example05A(@RequestHeader("Accept-Version") String acceptVersion) {
        return String.format("Received request with Accept-Version = [%s]\n", acceptVersion);
    }

    // curl -H "Accept-Version: v2.1" http://localhost:8080/example05B
    // curl http://localhost:8080/example05B
    @GetMapping("/example05B")
    @ResponseBody
    public String example05B(@RequestHeader(value = "Accept-Version", required = false) String acceptVersion) {
        return String.format("Received request with Accept-Version = [%s]\n", acceptVersion);
    }

    // curl -H "Accept-Version: v2.1" http://localhost:8080/example05C
    // curl http://localhost:8080/example05C
    @GetMapping("/example05C")
    @ResponseBody
    public String example05C(@RequestHeader(value = "Accept-Version", required = false, defaultValue = "N/A") String acceptVersion) {
        return String.format("Received request with Accept-Version = [%s]\n", acceptVersion);
    }

    // curl -H "Accept-Version: v2.1" http://localhost:8080/example05D
    // curl http://localhost:8080/example05D
    @GetMapping("/example05D")
    @ResponseBody
    public String example05D(@RequestHeader("Accept-Version") Optional<String> acceptVersion) {
        return String.format("Received request with Accept-Version = [%s]\n", acceptVersion.orElse("N/A"));
    }

    // curl -H "Accept-Version: v2.1" http://localhost:8080/example05E
    @GetMapping("/example05E")
    @ResponseBody
    public String example05E(@RequestHeader Map<String, String> headers) {
        return String.format("Received request with headers = [%s]\n", headers);
    }
}
