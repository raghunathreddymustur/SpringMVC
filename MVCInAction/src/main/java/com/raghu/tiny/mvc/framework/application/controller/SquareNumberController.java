package com.raghu.tiny.mvc.framework.application.controller;

import com.raghu.tiny.mvc.framework.application.view.SquareNumberView;
import com.raghu.tiny.mvc.framework.controller.ControllerMapping;
import com.raghu.tiny.mvc.framework.controller.IController;
import com.raghu.tiny.mvc.framework.model.Model;
import com.raghu.tiny.mvc.framework.model.SimpleModel;
import com.raghu.tiny.mvc.framework.pojo.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static java.lang.String.valueOf;

@ControllerMapping("/square-number")
public class SquareNumberController implements IController {
    @Override
    public ModelAndView handleRequest(HttpServletRequest req) {
        Model model = new SimpleModel();

        int number = Integer.parseInt(req.getParameter("number"));
        int numberSquare = (int) Math.pow(number, 2);

        model.set("number", valueOf(number));
        model.set("numberSquare", valueOf(numberSquare));

        return new ModelAndView(
                model,
                new SquareNumberView()
        );
    }
}
