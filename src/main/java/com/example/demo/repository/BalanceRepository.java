package com.example.demo.repository;

import com.example.demo.model.Balance;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BalanceRepository extends CrudRepository<Balance, Integer> {
    @Query("SELECT new java.lang.Double(SUM(bal.amount)) FROM Balance bal WHERE bal.isValid=true")
    Double getSummaryAmount();

    @Modifying
    @Query("UPDATE Balance bal " +
            "SET bal.credit= bal.credit+ :credit, bal.debit=bal.debit + :debit, " +
            "bal.amount = bal.debit-bal.credit " +
            "WHERE bal.id = :balance_id")
    void updateBalanceDebitAndCredit(@Param("balance_id")Integer balanceID, @Param("debit") Double debit, @Param("credit") Double credit);

    @Modifying
    @Query("UPDATE Balance bal SET bal.isValid= false WHERE bal.id = :id")
    void setIsValidToFalse(@Param("id") Integer id);

    @Query("SELECT bal.amount FROM Balance bal WHERE bal.id= :id")
    Double getAmountByID( @Param("id")Integer id);

    Balance findFirstByIsValidTrueOrderByCreditDesc();
    Balance findFirstByIsValidTrueOrderByDebitDesc();
    List<Balance> findAllByIsValidTrue();

    Optional<Balance> findByIdAndIsValidTrue(Integer ID);
    List<Balance> findAll();
    List<Balance> findAllByIsValidTrueOrderByCreateDate();
}


