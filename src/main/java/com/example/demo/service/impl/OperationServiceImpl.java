package com.example.demo.service.impl;

import com.example.demo.model.Article;
import com.example.demo.model.Balance;
import com.example.demo.model.Operation;
import com.example.demo.exception.NoEntityException;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.BalanceRepository;
import com.example.demo.repository.OperationRepository;
import com.example.demo.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("operationService")
@Transactional
public class OperationServiceImpl implements OperationService {
    private final OperationRepository opRep;
    private final ArticleRepository artRep;
    private final BalanceRepository balRep;

    @Autowired
    public OperationServiceImpl(OperationRepository opRep, ArticleRepository artRep, BalanceRepository balRep) {
        this.opRep = opRep;
        this.artRep = artRep;
        this.balRep = balRep;
    }

    @Override
    public Operation createNewOperation(Integer article_id, Double debit, Double credit, Date create_date, Integer balance_id) throws NoEntityException {
        Article art = artRep.findByIDAndIsValidTrue(article_id).orElseThrow(()->new NoEntityException(article_id));
        Balance bal = balRep.findByIDAndIsValidTrue(balance_id).orElseThrow(()->new NoEntityException(balance_id));
        Operation newOp = new Operation(art, debit,credit,create_date,bal);

        balRep.updateBalanceDebitAndCredit(balance_id,debit,credit);
        return opRep.save(newOp);
    }

    @Override
    public List<Operation> getAllOperationsByCurrentArticle(String articleName) {
        return opRep.findAllByArticle(articleName);
    }

    @Override
    public List<Operation> getAllOperationsByCurrentBalanceForThePeriod(Integer balance_id, Date begin, Date end){
        return opRep.findAllByBalanceAndCreateDateBetween(balance_id,begin,end);
    }

    @Override
    public List<Operation> getAllOperationsForThePeriod(Date begin, Date end) {
        return opRep.findAllByCreateDateBetween(begin,end);
    }

    @Override
    public List<Article> rankArticlesOfDebitForThePeriod(Date begin, Date end) {
        return opRep.getArticlesRankedByDebitForThePeriod(begin, end);
    }

    @Override
    public List<Article> rankArticlesOfCreditForThePeriod(Date begin, Date end) {
        return opRep.getArticlesRankedByCreditForThePeriod(begin, end);
    }

    @Override
    public Article getMostPopularArticleOfThePeriod(Date begin, Date end) {
        return opRep.getArticlesRankedDescByTheirPopularityForThePeriod(begin, end).get(0);
    }

    @Override
    public Balance getMostPopularBalanceOfThePeriod(Date begin, Date end) {
        return opRep.getBalancesRankedDescByTheirPopularityForThePeriod(begin, end).get(0);
    }

    @Override
    public Double getSummaryDebitOfThisArticleForThePeriod(String name, Date begin, Date end) {
        return opRep.getSummaryDebitOfArticleForThePeriod(name,begin,end);
    }
}
