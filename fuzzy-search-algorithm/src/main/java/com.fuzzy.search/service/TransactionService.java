package com.fuzzy.search.service;

import com.fuzzy.search.domain.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> search(String query);
}
