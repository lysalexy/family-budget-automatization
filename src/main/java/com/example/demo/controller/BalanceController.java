package com.example.demo.controller;

import com.example.demo.model.Balance;
import com.example.demo.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class BalanceController {
    @Autowired
    private BalanceService balServ;

    @PostMapping("/balance/add")
    String addBalance(@RequestParam("createDate")
                      @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date createDate,
                      @RequestParam("debit") Double debit,
                      @RequestParam("credit")Double credit)
    {
        if ((debit>=0)&&(credit>=0)){
        balServ.createNewBalance(createDate, debit, credit);
        return "Balance was created";
        }
        return "Debit and credit should be positive numbers";
    }

    @GetMapping("/balance/getAll")
    List<Balance> getAllBalances()
    {
        return balServ.getAll();
    }

    @GetMapping("/balance/getAvailable")
    List<Balance> getAvailableBalances()
    {
        return balServ.getListOfAvailableBalances();
    }

    @GetMapping("/balance/getMostProfitable")
    Balance getMostProfitableBalance()
    {
        return balServ.getMostProfitableBalance();
    }

    @GetMapping("/balance/getMostSpending")
    Balance getMostSpendingBalance()
    {
        return balServ.getMostSpendingBalance();
    }

    @GetMapping("/balance/getAmountByID")
    Double getAmountByBalanceID(@RequestParam("ID") Integer id)
    {

        return balServ.getAmountByID(id);
    }

    @GetMapping("/balance/getSummaryAmount")
    Double getSummaryAmount()
    {
        return balServ.getSummaryAmountOfMoneyInAllBalances();
    }

    @GetMapping("/balance/rankByCreateDate")
    List<Balance> rankByCreateDate() {
        return balServ.rankBalanceByCreateDate();
    }

    @PutMapping("/balance/deleteByID")
    String deleteBalanceByID(@RequestParam ("ID")Integer id)
    {
        balServ.deleteBalance(id);
        return "Balance was deleted";
    }
}
