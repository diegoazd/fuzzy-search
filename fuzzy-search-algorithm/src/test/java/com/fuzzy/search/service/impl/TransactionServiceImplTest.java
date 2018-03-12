package com.fuzzy.search.service.impl;

import com.fuzzy.search.domain.Transaction;
import com.fuzzy.search.service.TransactionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import static org.junit.Assert.*;

public class TransactionServiceImplTest {

    TransactionService transactionService;

    @Before
    public void setUp() {
        transactionService = new TransactionServiceImpl();
    }

    @Test
    public void shouldFindTwoCoincidencesOnCardLastFour() {
        List<Transaction> list = transactionService.search("2540");
        assertNotNull(list);
        assertTrue(list.size() == 2);
    }

    @Test
    public void shouldFindOneCoincidenceOnAmount() {
        List<Transaction> list = transactionService.search("0.45");
        assertNotNull(list);
        assertTrue(list.size() == 1);

        list = transactionService.search("2580.7");
        assertNotNull(list);
        assertTrue(list.size() == 1);

        list = transactionService.search("7774.3");
        assertNotNull(list);
        assertTrue(list.size() == 1);
    }

    @Test
    public void shouldFindTwoCoincidenceOnDate() {
        List<Transaction> list = transactionService.search("27-01-2018 01:00");
        assertNotNull(list);
        assertTrue(list.size() == 3);

        list = transactionService.search("27-01-2018");
        assertNotNull(list);
        assertTrue(list.size() == 3);
    }

}