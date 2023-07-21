package com.raghu.tiny.mvc.framework.controller;

import com.raghu.tiny.mvc.framework.pojo.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public interface IController {
    ModelAndView handleRequest(HttpServletRequest req);
}
