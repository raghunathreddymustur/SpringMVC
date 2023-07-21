package com.raghu.tiny.mvc.framework.application.controller;

import com.raghu.tiny.mvc.framework.application.view.IndexView;
import com.raghu.tiny.mvc.framework.controller.ControllerMapping;
import com.raghu.tiny.mvc.framework.controller.IController;
import com.raghu.tiny.mvc.framework.model.SimpleModel;
import com.raghu.tiny.mvc.framework.pojo.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerMapping("/")
public class IndexController implements IController {
    @Override
    public ModelAndView handleRequest(HttpServletRequest req) {
        return new ModelAndView(
                new SimpleModel(),
                new IndexView()
        );
    }
}
