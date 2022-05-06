package com.example.demo.controller;

import com.example.demo.exception.NoEntityException;
import com.example.demo.model.Article;
import com.example.demo.service.ArticleService;
import com.example.demo.web.ArticleWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {
    @Autowired
    private ArticleService artServ;

    @GetMapping("/article")
    List<Article> getAvailableArticles ()
    {
        return artServ.getListOfAvailableArticles();
    }

//    @PostMapping("/article/add")
//    Article addArticle( @RequestParam("name")String name){
//        return  artServ.createNewArticle(name);
//        ///return "Article was added to DB";
//    }

    @PostMapping("/article/add")
    Article addArticle(@RequestBody Article article){
        return artServ.createNewArticle(article.getName());
        //return "Article was added to DB";
    }


    @PutMapping("/article/rename")
    String renameArticle( @RequestParam ("oldName")String oldName,
                          @RequestParam ("newName")String newName)
    {
        artServ.renameArticle(oldName, newName);
        return "Article's name was changed to "+newName;
    }

    @GetMapping("/article/getByName")
    String findArticleByName(@RequestParam ("name")String name)
    {
        try{
            return artServ.findArticleByName(name).toString();}
        catch (NoEntityException e)
        {
            return e.toString();
        }
    }

//    @GetMapping("/article/getByName")
//    String findArticleByName(@RequestBody ArticleWeb artWeb)
//    {
//        try{
//            return artServ.findArticleByName(artWeb.getName()).toString();}
//        catch (NoEntityException e)
//        {
//            return e.toString();
//        }
//    }
    @PutMapping("/article/deleteByName")
    String deleteArticleByName(@RequestParam ("name")String name)
    {
        artServ.deleteArticle(name);
        return "Article was deleted";
    }
}
