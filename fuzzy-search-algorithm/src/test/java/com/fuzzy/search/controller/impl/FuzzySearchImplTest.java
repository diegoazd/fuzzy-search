package com.fuzzy.search.controller.impl;

import com.fuzzy.search.controller.FuzzySearch;
import com.fuzzy.search.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class FuzzySearchImplTest {

    FuzzySearch fuzzySearch;

    @Before
    public void setUp() {
        fuzzySearch = new FuzzySearchImpl();
        TransactionService transactionService = Mockito.mock(TransactionService.class);
        ((FuzzySearchImpl)fuzzySearch).transactionService = transactionService;
    }

    @Test
    public void shouldReturnNull() {
        fuzzySearch.fuzzySearch("");
    }
}