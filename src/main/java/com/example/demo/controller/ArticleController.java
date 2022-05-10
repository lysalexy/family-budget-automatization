package com.example.demo.controller;

import com.example.demo.exception.AlreadyExists;
import com.example.demo.exception.NoEntityException;
import com.example.demo.model.Article;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
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


    @PostMapping("/article/add")
    Article addArticle(@RequestBody Article article){
        try{
        return artServ.createNewArticle(article.getName());
        }
        catch (AlreadyExists e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED,"Article with with name already exist",e);
        }
    }


    @PutMapping("/article/rename")
    String renameArticle( @RequestParam ("oldName")String oldName,
                          @RequestParam ("newName")String newName)
    {
        artServ.renameArticle(oldName, newName);
        return "Article's name was changed to "+newName;
    }

    @GetMapping("/article/getByName")
    Article findArticleByName(@RequestParam ("name")String name)
    {
        try{
            return artServ.findArticleByName(name) ;}
        catch (NoEntityException e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED,"Article with this name does not exist",e);
        }
    }

    @PutMapping("/article/deleteByName")
    void deleteArticleByName(@RequestParam ("name")String name)
    {
        artServ.deleteArticle(name);
        /// return "Article was deleted";
    }
}
