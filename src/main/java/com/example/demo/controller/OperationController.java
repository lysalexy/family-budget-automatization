package com.example.demo.controller;

import com.example.demo.exception.InvalidOperationException;
import com.example.demo.exception.NoEntityException;
import com.example.demo.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class OperationController {
    @Autowired
    private OperationService opServ;

    @PostMapping("/operation/add")
    String addOperation(@RequestParam("articleID") Integer article_id,
                        @RequestParam("debit") Double debit,
                        @RequestParam("credit")Double credit,
                        @RequestParam("createDate")
                        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date create_date,
                        @RequestParam("balanceID") Integer balance_id){
        try {
            if ((debit>=0)&&(credit<=0)||((debit<=0)&&(credit>=0)))
            {
                opServ.createNewOperation(article_id, debit, credit, create_date, balance_id);
                return "Operation was added to DB";
            }
            else
            {
                throw new InvalidOperationException("Operation can't be with both debit and credit");
            }
        }
        catch (NoEntityException |InvalidOperationException e)
        {
            return e.toString();
        }
    }

    @GetMapping("/operation/getAllByCurrentArticle")
    String getAllOperationsByCurrentArticle(@RequestParam("articleName") String articleName)
    {
        return opServ.getAllOperationsByCurrentArticle(articleName).toString();
    }

    @GetMapping ("/operation/getAllByCurrentBalanceForThePeriod")
    String getAllOperationsByCurrentBalanceForThePeriod (@RequestParam ("balanceID") Integer balance_id,
                                                         @RequestParam ("begin")
                                                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date begin,
                                                         @RequestParam("end")
                                                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date end)
    {
        return opServ.getAllOperationsByCurrentBalanceForThePeriod(balance_id, begin, end).toString();
    }

    @GetMapping ("/operation/getAllForThePeriod")
    String getAllOperationsForThePeriod (@RequestParam ("begin")
                                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date begin,
                                         @RequestParam("end")
                                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date end)
    {
        return opServ.getAllOperationsForThePeriod(begin, end).toString();
    }

    @GetMapping ("/operation/rankArticlesOfDebitForThePeriod")
    String rankArticlesOfDebitForThePeriod (@RequestParam ("begin")
                                            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date begin,
                                            @RequestParam("end")
                                            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date end)
    {
        return opServ.rankArticlesOfDebitForThePeriod(begin, end).toString();
    }

    @GetMapping ("/operation/rankArticlesOfCreditForThePeriod")
    String rankArticlesOfCreditForThePeriod (@RequestParam ("begin")
                                             @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date begin,
                                             @RequestParam("end")
                                             @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date end)
    {
        return opServ.rankArticlesOfCreditForThePeriod(begin, end).toString();
    }

    @GetMapping("/operation/getSummaryDebitByArticleNameForThePeriod")
    String getSummaryDebitOfThisArticleForThePeriod(@RequestParam ("articleName") String name,
                                                    @RequestParam ("begin")
                                                    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date begin,
                                                    @RequestParam("end")
                                                    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date end)
    {
        return opServ.getSummaryDebitOfThisArticleForThePeriod(name, begin, end).toString();
    }

    @GetMapping("/operation/getMostPopularBalanceOfThePeriod")
    String getMostPopularBalanceOfThePeriod (@RequestParam ("begin")
                                             @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date begin,
                                             @RequestParam("end")
                                             @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date end)
    {
        return opServ.getMostPopularBalanceOfThePeriod(begin, end).toString();
    }

    @GetMapping("/operation/getMostPopularArticleOfThePeriod")
    String getMostPopularArticleOfThePeriod(@RequestParam ("begin")
                                            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date begin,
                                            @RequestParam("end")
                                            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date end)
    {
        return opServ.getMostPopularArticleOfThePeriod(begin, end).toString();
    }
}
