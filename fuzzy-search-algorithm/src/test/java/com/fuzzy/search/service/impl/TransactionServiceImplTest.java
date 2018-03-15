package com.fuzzy.search.service.impl;

import com.fuzzy.search.domain.Transaction;
import com.fuzzy.search.service.TransactionService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
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

        assertEquals("2544", list.get(0).getCardLastFour());
        assertEquals(BigDecimal.valueOf(112.98), list.get(0).getAmount());
        assertEquals("27-01-2018 12:34", list.get(0).getDateTime());

        assertEquals("2544", list.get(1).getCardLastFour());
        assertEquals(BigDecimal.valueOf(1345.98), list.get(1).getAmount());
        assertEquals("27-01-2018 12:34", list.get(1).getDateTime());
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
        assertEquals("2544", list.get(0).getCardLastFour());
        assertEquals(BigDecimal.valueOf(112.98), list.get(0).getAmount());
        assertEquals("27-01-2018 12:34", list.get(0).getDateTime());

        assertEquals("2544", list.get(1).getCardLastFour());
        assertEquals(BigDecimal.valueOf(1345.98), list.get(1).getAmount());
        assertEquals("27-01-2018 12:34", list.get(1).getDateTime());

        assertEquals("4444", list.get(2).getCardLastFour());
        assertEquals(BigDecimal.valueOf(2580.70), list.get(2).getAmount());
        assertEquals("27-01-2018 12:34", list.get(2).getDateTime());
    }

    @Test
    public void shouldReturnAllForEmptyString() {
        List<Transaction> list = transactionService.search("");
        assertNotNull(list);
        assertTrue(list.size() == 10);
    }

}