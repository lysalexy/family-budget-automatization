package com.example.demo.service;

import com.example.demo.exception.AlreadyExists;
import com.example.demo.model.Article;
import com.example.demo.exception.NoEntityException;
import java.util.List;

public interface ArticleService {
    Article createNewArticle (String name) throws AlreadyExists;
    void renameArticle(String oldName, String newName);
    Article findArticleByName(String name) throws NoEntityException;
    void deleteArticle (String name);

    List<Article> getListOfAvailableArticles();
    List<Article> getListOfUnavailableArticles();
}
