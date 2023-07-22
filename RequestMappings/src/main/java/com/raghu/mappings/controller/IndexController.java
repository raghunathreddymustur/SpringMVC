package com.raghu.mappings.controller;

import com.raghu.mappings.dao.ArticlesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @Autowired
    private ArticlesDao articlesDao;

    @GetMapping({"/", "/index", "/home"})
    public String index(Model model) {
        model.addAttribute("articles", articlesDao.findAll());

        return "index";
    }
}
