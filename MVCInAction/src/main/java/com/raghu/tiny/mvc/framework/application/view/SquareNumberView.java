package com.raghu.tiny.mvc.framework.application.view;

import com.raghu.tiny.mvc.framework.model.Model;
import com.raghu.tiny.mvc.framework.view.View;

public class SquareNumberView implements View {
    @Override
    public String render(Model model) {
        return String.format(
                "Square number of [%s] is [%s]",
                model.get("number"), model.get("numberSquare")
        );
    }
}
