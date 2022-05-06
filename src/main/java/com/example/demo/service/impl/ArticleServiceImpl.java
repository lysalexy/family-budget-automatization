package com.example.demo.service.impl;

import com.example.demo.model.Article;
import com.example.demo.exception.NoEntityException;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("articleService")
@Transactional
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository artRep;

    @Autowired
    public ArticleServiceImpl(ArticleRepository artRep)
    {
        this.artRep=artRep;
    }


    @Override
   public Article createNewArticle( String name){
        Article newArt = new Article(name);
        return artRep.save(newArt);
    }

    @Override
    public void renameArticle(String oldName, String newName) {
        artRep.renameArticle(newName,oldName);
    }

    @Override
    public Article findArticleByName(String name) throws NoEntityException {
       return artRep.findByNameAndIsValidTrue(name).orElseThrow(()->new NoEntityException(name));
    }

    @Override
    public void deleteArticle(String name){
        artRep.setIsValidToFalse(name);
    }

    @Override
    public List<Article> getListOfAvailableArticles() {
        return artRep.findAllByIsValidTrue();
    }
}
