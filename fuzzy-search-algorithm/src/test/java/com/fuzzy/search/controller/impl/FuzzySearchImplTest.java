package com.fuzzy.search.controller.impl;

import com.fuzzy.search.controller.FuzzySearch;
import com.fuzzy.search.domain.Transaction;
import com.fuzzy.search.service.TransactionService;
import com.fuzzy.search.service.impl.TransactionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FuzzySearchImplTest {

    FuzzySearch fuzzySearch;
    TransactionService transactionService;

    @Before
    public void setUp() {
        fuzzySearch = new FuzzySearchImpl();
        transactionService = Mockito.mock(TransactionService.class);
        ((FuzzySearchImpl)fuzzySearch).transactionService = transactionService;
    }

    @Test
    public void shouldReturnNull() {
        assertTrue(fuzzySearch.getAll().isEmpty());
    }

    @Test
    public void shouldReturnOnevalue() {
        List<Transaction> list = new ArrayList<>();
        Transaction transaction = new Transaction(null, null, null);
        list.add(transaction);
        Mockito.when(transactionService.search(Mockito.anyString()))
                .thenReturn(list);

        assertEquals(1, transactionService.search("").size());
    }
}