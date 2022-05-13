package com.example.demo.repository;

import com.example.demo.model.Article;
import com.example.demo.model.Balance;
import com.example.demo.model.Operation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OperationRepository extends CrudRepository<Operation, Integer> {
    List<Operation> findAll();

    @Query("SELECT op.article " +
            "FROM Operation op " +
            "WHERE  op.createDate >= :begin and op.createDate<= :end " +
            "AND op.article.isValid = TRUE AND op.balance.isValid = TRUE "+
            "GROUP BY op.article.name " +
            "ORDER BY COUNT(op.article.name) DESC")
    List<Article> getArticlesRankedDescByTheirPopularityForThePeriod(@Param("begin")Date begin, @Param("end")Date end);

    @Query("SELECT op.balance " +
            "FROM Operation op " +
            "WHERE op.createDate BETWEEN :begin AND :end " +
            "AND op.article.isValid = TRUE AND op.balance.isValid = TRUE "+
            "GROUP BY op.balance.id " +
            "ORDER BY COUNT(op.balance.id) DESC")
    List<Balance> getBalancesRankedDescByTheirPopularityForThePeriod(@Param("begin")Date begin, @Param("end")Date end);

    @Query("SELECT op.article " +
            "FROM Operation op " +
            "WHERE op.createDate BETWEEN :begin AND :end " +
            "AND op.article.isValid = TRUE AND op.balance.isValid = TRUE " +
            "GROUP BY op.article.name " +
            "ORDER BY SUM(op.debit) ASC")
    List<Article> getArticlesRankedByDebitForThePeriod(@Param("begin")Date begin, @Param("end")Date end);

    @Query("SELECT op.article " +
            "FROM Operation op " +
            "WHERE op.createDate BETWEEN :begin AND :end " +
            "AND op.article.isValid = TRUE AND op.balance.isValid = TRUE " +
            "GROUP BY op.article.name " +
            "ORDER BY SUM(op.credit) ASC")
    List<Article> getArticlesRankedByCreditForThePeriod(@Param("begin")Date begin, @Param("end")Date end);

    @Query("SELECT op FROM Operation op WHERE op.createDate BETWEEN :begin AND :end " +
            "AND op.article.isValid = TRUE AND op.balance.isValid = TRUE")
    List<Operation> findAllByCreateDateBetween(@Param("begin") Date begin,@Param("end")Date end);

    @Query("SELECT op FROM Operation op " +
            "WHERE op.article.name = :articleName AND op.article.isValid = TRUE " +
            "AND op.balance.isValid = TRUE")
    List<Operation> findAllByArticle(@Param("articleName")String artName);

    @Query("SELECT op FROM Operation op WHERE op.balance.id = :balance_id " +
            "AND op.article.isValid = TRUE AND op.balance.isValid = TRUE " +
            "AND op.createDate BETWEEN :begin AND :end")
    List<Operation> findAllByBalanceAndCreateDateBetween(@Param("balance_id")Integer bal, @Param("begin") Date begin, @Param("end")Date end);
}
