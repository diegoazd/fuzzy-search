package com.fuzzy.search.controller;

import com.fuzzy.search.domain.Transaction;

import java.util.List;

public interface FuzzySearch {

    List<Transaction> fuzzySearch(String query);
    List<Transaction> getAll();
}
