package com.raghu.controller.para.annotations.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class Example04CookieValue {
    // curl -c cookie.txt -b cookie.txt http://localhost:8080/example04/set-cookie
    @GetMapping("/example04/set-cookie")
    @ResponseBody
    public String example04(HttpServletResponse servletResponse) {
        servletResponse.addCookie(new Cookie("user-id", "15"));

        return "Cookie set successfully\n";
    }

    // curl -c cookie.txt -b cookie.txt http://localhost:8080/example04/read-cookie1
    @GetMapping("/example04/read-cookie1")
    @ResponseBody
    public String example04ReadCookie1(@CookieValue("user-id") String userId) {
        return String.format("user-id from cookie = [%s]\n", userId);
    }

    // curl -c cookie.txt -b cookie.txt http://localhost:8080/example04/read-cookie2
    @GetMapping("/example04/read-cookie2")
    @ResponseBody
    public String example04ReadCookie2(@CookieValue("user-id") Cookie cookie) {
        return String.format(
                "Received cookie name = [%s], value = [%s]\n",
                cookie.getName(), cookie.getValue()
        );
    }

    // curl -c cookie.txt -b cookie.txt http://localhost:8080/example04/read-cookie3
    // curl http://localhost:8080/example04/read-cookie3
    @GetMapping("/example04/read-cookie3")
    @ResponseBody
    public String example04ReadCookie3(@CookieValue(value = "user-id", required = false) String userId) {
        return String.format("user-id from cookie = [%s]\n", userId);
    }

    // curl -c cookie.txt -b cookie.txt http://localhost:8080/example04/read-cookie4
    // curl http://localhost:8080/example04/read-cookie4
    @GetMapping("/example04/read-cookie4")
    @ResponseBody
    public String example04ReadCookie4(@CookieValue(value = "user-id", required = false, defaultValue = "N/A") String userId) {
        return String.format("user-id from cookie = [%s]\n", userId);
    }

    // curl -c cookie.txt -b cookie.txt http://localhost:8080/example04/read-cookie5
    // curl http://localhost:8080/example04/read-cookie5
    @GetMapping("/example04/read-cookie5")
    @ResponseBody
    public String example04ReadCookie5(@CookieValue(value = "user-id") Optional<String> userId) {
        return String.format("user-id from cookie = [%s]\n", userId.orElse("N/A"));
    }
}
