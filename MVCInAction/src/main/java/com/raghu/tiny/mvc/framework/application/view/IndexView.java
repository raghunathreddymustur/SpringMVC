package com.raghu.tiny.mvc.framework.application.view;

import com.raghu.tiny.mvc.framework.model.Model;
import com.raghu.tiny.mvc.framework.view.View;

public class IndexView implements View {
    @Override
    public String render(Model model) {
        return "Hello from Index Controller and View<br/>" +
                "Please visit one of following sites:<br/>" +
                "<ul>" +
                "<li><a href='/add-numbers?numberA=2&numberB=4'>/add-numbers?numberA=2&numberB=4</a></li>" +
                "<li><a href='/square-number?number=4'>/square-number?number=4</a></li>" +
                "</ul>";
    }
}
