package intro.controller;

import intro.dao.ArticlesDao;
import intro.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.websocket.server.PathParam;

@Controller
public class IndexController {
    @Autowired
    private ArticlesDao articlesDao;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("articles", articlesDao.findAll());
        model.addAttribute("newArticle", new Article());

        return "index";
    }
    @PostMapping("save-article")
    public String saveArticle(@ModelAttribute Article article) {
        articlesDao.save(article);

        return "redirect:/";
    }

    @PostMapping("delete-article")
    public String deleteArticle(@PathParam("id") int id) {
        articlesDao.deleteById(id);

        return "redirect:/";
    }
}
