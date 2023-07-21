package com.raghu.tiny.mvc.framework.application.controller;

import com.raghu.tiny.mvc.framework.application.view.AddNumbersView;
import com.raghu.tiny.mvc.framework.controller.ControllerMapping;
import com.raghu.tiny.mvc.framework.controller.IController;
import com.raghu.tiny.mvc.framework.model.Model;
import com.raghu.tiny.mvc.framework.model.SimpleModel;
import com.raghu.tiny.mvc.framework.pojo.ModelAndView;
import com.raghu.tiny.mvc.framework.view.View;

import javax.servlet.http.HttpServletRequest;

import static java.lang.String.valueOf;

@ControllerMapping("/add-numbers")
public class AddNumbersController  implements IController {
    @Override
    public ModelAndView handleRequest(HttpServletRequest req) {
        Model model = new SimpleModel();

        int numberA = Integer.parseInt(req.getParameter("numberA"));
        int numberB = Integer.parseInt(req.getParameter("numberB"));

        model.set("numberA", valueOf(numberA));
        model.set("numberB", valueOf(numberB));
        model.set("result", valueOf(numberA + numberB));

        return new ModelAndView(
                model,
                (View) new AddNumbersView()
        );
    }
}
