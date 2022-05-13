package com.example.demo.service;

import com.example.demo.model.Article;
import com.example.demo.model.Balance;
import com.example.demo.model.Operation;
import com.example.demo.exception.NoEntityException;

import java.util.Date;
import java.util.List;

public interface OperationService {
    Operation createNewOperation (Integer article_id, Double debit, Double credit, Date create_date, Integer balance_id) throws NoEntityException;

    List<Operation> getAllOperations();
    List<Operation> getAllOperationsByCurrentArticle(String articleName);
    List<Operation> getAllOperationsByCurrentBalanceForThePeriod(Integer balance_id, Date begin, Date end);
    List<Operation> getAllOperationsForThePeriod(Date begin, Date end);

    List<Article> rankArticlesOfDebitForThePeriod(Date begin, Date end);
    List<Article> rankArticlesOfCreditForThePeriod(Date begin, Date end);

    Balance getMostPopularBalanceOfThePeriod(Date begin, Date end);
    Article getMostPopularArticleOfThePeriod(Date begin, Date end);
}
