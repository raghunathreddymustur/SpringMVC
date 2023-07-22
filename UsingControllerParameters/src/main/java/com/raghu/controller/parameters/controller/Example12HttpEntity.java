package com.raghu.controller.parameters.controller;

import com.spring.professional.exam.tutorial.module05.question09.ds.Address;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Example12HttpEntity {
    // curl -X POST -H 'Content-Type: application/json' -d '{ "firstName": "John", "lastName": "Doe", "streetAddress": "455 Larkspur Dr. Apt 23", "city": "Baviera", "stateCode": "CA", "zipCode": "92908", "country": "United States" }' localhost:8080/actionL
    @PostMapping("/actionL")
    @ResponseBody
    public String actionL(HttpEntity<Address> httpEntity) {
        return format(
                "Received request from host = [%s],\nAddress = [%s]\n",
                httpEntity.getHeaders().getHost(),
                httpEntity.getBody()
        );
    }
}
