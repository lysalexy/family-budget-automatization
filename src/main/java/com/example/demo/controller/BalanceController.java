package com.example.demo.controller;

import com.example.demo.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
        balServ.createNewBalance(createDate, debit, credit);
        return "Balance was created";
    }

    @GetMapping("/balance/getAll")
    String getAllBalances()
    {
        return balServ.getAll().toString();
    }

    @GetMapping("/balance/getAvailable")
    String getAvailableBalances()
    {
        return balServ.getListOfAvailableBalances().toString();
    }

    @GetMapping("/balance/getMostProfitable")
    String getMostProfitableBalance()
    {
        return balServ.getMostProfitableBalance().toString();
    }

    @GetMapping("/balance/getMostSpending")
    String getMostSpendingBalance()
    {
        return balServ.getMostSpendingBalance().toString();
    }

    @GetMapping("/balance/getAmountByID")
    String getAmountByBalanceID(@RequestParam("ID") Integer id)
    {
        return balServ.getAmountByID(id).toString();
    }

    @GetMapping("/balance/getSummaryAmount")
    String getSummaryAmount()
    {
        return balServ.getSummaryAmountOfMoneyInAllBalances().toString();
    }

    @GetMapping("/balance/rankByCreateDate")
    String rankByCreateDate() {
        return balServ.rankBalanceByCreateDate().toString();
    }

    @PutMapping("/balance/deleteByID")
    String deleteBalanceByID(@RequestParam ("ID")Integer id)
    {
        balServ.deleteBalance(id);
        return "Balance was deleted";
    }
}
