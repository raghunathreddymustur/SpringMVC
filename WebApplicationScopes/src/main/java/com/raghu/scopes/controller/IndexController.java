package com.raghu.scopes.controller;

import com.raghu.scopes.beans.ApplicationScopeBean;
import com.raghu.scopes.beans.RequestScopeBean;
import com.raghu.scopes.beans.SessionScopeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class IndexController {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private RequestScopeBean requestScopeBean;
    @Autowired
    private SessionScopeBean sessionScopeBean;
    @Autowired
    private ApplicationScopeBean applicationScopeBean;

    @RequestMapping(path = "/", method = {GET, POST})
    public String index(Model model) {
        model.addAttribute("servletContext", webApplicationContext.getServletContext());
        model.addAttribute("requestScopeValue", requestScopeBean.getValue());
        model.addAttribute("sessionScopeValue", sessionScopeBean.getValue());
        model.addAttribute("applicationScopeValue", applicationScopeBean.getValue());

        return "index";
    }

    @PostMapping("saveRequestScopeValue")
    public String saveRequestScopeValue(@RequestParam("requestScopeValue") int requestScopeValue) {
        requestScopeBean.setValue(requestScopeValue);

        return "forward:/";
    }

    @PostMapping("saveSessionScopeValue")
    public String saveSessionScopeValue(@RequestParam("sessionScopeValue") int sessionScopeValue) {
        sessionScopeBean.setValue(sessionScopeValue);

        return "forward:/";
    }

    @PostMapping("saveApplicationScopeValue")
    public String saveApplicationScopeValue(@RequestParam("applicationScopeValue") int applicationScopeValue) {
        applicationScopeBean.setValue(applicationScopeValue);

        return "forward:/";
    }
}
