package com.fuzzy.search.controller.impl;

import com.fuzzy.search.controller.FuzzySearch;
import com.fuzzy.search.domain.Transaction;
import com.fuzzy.search.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FuzzySearchImpl implements FuzzySearch {

    @Autowired
    TransactionService transactionService;

    @Override
    @GetMapping(value = "search/{query}/transaction", produces = "application/json")
    public List<Transaction> fuzzySearch(@PathVariable String query) {
        System.out.println(query);
        return transactionService.search(query);
    }
}
