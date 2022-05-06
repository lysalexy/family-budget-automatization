package com.example.demo.service.impl;

import com.example.demo.model.Balance;
import com.example.demo.repository.BalanceRepository;
import com.example.demo.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("balanceService")
@Transactional
public class BalanceServiceImpl implements BalanceService {
    private final BalanceRepository balRep;

    @Autowired
    public BalanceServiceImpl(BalanceRepository balRep) {
        this.balRep = balRep;
    }

    @Override
        public Balance createNewBalance(Date createDate, Double debit, Double credit) {
        Balance newBal = new Balance(createDate,debit,credit);
        return balRep.save(newBal);
    }

    @Override
    public List<Balance> getAll() {
         return balRep.findAll();
    }

    @Override
    public Balance getMostProfitableBalance() {
        return balRep.findFirstByIsValidTrueOrderByCreditDesc();
    }

    @Override
    public Balance getMostSpendingBalance() {
        return balRep.findFirstByIsValidTrueOrderByDebitDesc();
    }

    @Override
    public List<Balance> getListOfAvailableBalances() {
         return balRep.findAllByIsValidTrue();
    }

    @Override
    public Double getAmountByID(Integer id) {

        return balRep.getAmountByID(id);
    }

    @Override
    public Double getSummaryAmountOfMoneyInAllBalances() {
        return balRep.getSummaryAmount();
    }

    @Override
    public List<Balance> rankBalanceByCreateDate() {
        return balRep.findAllByIsValidTrueOrderByCreateDate();
    }

    @Override
    public void deleteBalance(Integer id) {
        balRep.setIsValidToFalse(id);
    }
}
