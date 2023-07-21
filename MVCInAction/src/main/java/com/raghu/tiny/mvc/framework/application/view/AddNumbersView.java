package com.raghu.tiny.mvc.framework.application.view;

import com.raghu.tiny.mvc.framework.model.Model;

public class AddNumbersView {
    @Override
    public String render(Model model) {
        return String.format(
                "Result of adding numberA = [%s] and numberB = [%s] is [%s]",
                model.get("numberA"), model.get("numberB"), model.get("result")
        );
    }
}
