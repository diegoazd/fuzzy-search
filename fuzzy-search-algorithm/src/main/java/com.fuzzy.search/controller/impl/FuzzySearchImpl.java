package com.fuzzy.search.controller.impl;

import com.fuzzy.search.controller.FuzzySearch;
import com.fuzzy.search.domain.Transaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController(value = "/search")
public class FuzzySearchImpl implements FuzzySearch {

    @Override
    @GetMapping
    public Set<Transaction> fuzzySearch(String query) {
        Set<Transaction> set = new HashSet<>();
        return null;
    }
}
