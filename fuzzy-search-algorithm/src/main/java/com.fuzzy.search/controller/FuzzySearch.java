package com.fuzzy.search.controller;

import com.fuzzy.search.domain.Transaction;

import java.util.Set;

public interface FuzzySearch {

    Set<Transaction> fuzzySearch(String query);
}
