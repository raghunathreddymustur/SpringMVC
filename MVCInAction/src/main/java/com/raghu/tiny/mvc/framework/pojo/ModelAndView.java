package com.raghu.tiny.mvc.framework.pojo;

import com.raghu.tiny.mvc.framework.model.Model;
import com.raghu.tiny.mvc.framework.view.View;

public class ModelAndView {
    private Model model;
    private View view;

    public ModelAndView(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public Model getModel() {
        return model;
    }

    public View getView() {
        return view;
    }
}
