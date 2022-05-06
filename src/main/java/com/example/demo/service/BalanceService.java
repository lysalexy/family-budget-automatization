package com.example.demo.service;

import com.example.demo.model.Balance;

import java.util.Date;
import java.util.List;

public interface BalanceService {
    Balance createNewBalance(Date createDate, Double debit, Double credit);

    List<Balance> getAll();
    Balance getMostProfitableBalance();
    Balance getMostSpendingBalance();
    List<Balance> getListOfAvailableBalances();
    Double getAmountByID(Integer id);
    Double getSummaryAmountOfMoneyInAllBalances();
    List<Balance> rankBalanceByCreateDate();

    void deleteBalance(Integer id);
}
