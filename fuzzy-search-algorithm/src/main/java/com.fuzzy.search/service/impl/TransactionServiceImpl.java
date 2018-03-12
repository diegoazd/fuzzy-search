package com.fuzzy.search.service.impl;

import com.fuzzy.search.domain.Transaction;
import com.fuzzy.search.service.TransactionService;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    DateTimeFormatter formatter;
    DateTimeFormatter shortFormat;

    List<Transaction> transactions = null;

    public TransactionServiceImpl() {
        formatter = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm");
        shortFormat = DateTimeFormat.forPattern("dd-MM-yyyy");
        transactions = new ArrayList<>();

        Transaction t = new Transaction(BigDecimal.valueOf(112.98d),
                formatter.parseDateTime("27-01-2018 12:34"), "2544");

        Transaction t2 = new Transaction(BigDecimal.valueOf(0.45d),
                formatter.parseDateTime("01-12-2017 09:36"), "4434");

        Transaction t3 = new Transaction(BigDecimal.valueOf(95.99d),
                formatter.parseDateTime("23-11-2017 14:34"), "3011");

        Transaction t4 = new Transaction(BigDecimal.valueOf(7774.32d),
                formatter.parseDateTime("17-07-2017 03:34"), "6051");

        Transaction t5 = new Transaction(BigDecimal.valueOf(1345.98d),
                formatter.parseDateTime("27-01-2018 12:34"), "2544");

        Transaction t6 = new Transaction(BigDecimal.valueOf(2580.70d),
                formatter.parseDateTime("27-01-2018 12:34"), "4444");

        Transaction t7 = new Transaction(BigDecimal.valueOf(45.00d),
                formatter.parseDateTime("10-02-2018 02:34"), "0110");

        Transaction t8 = new Transaction(BigDecimal.valueOf(1.00d),
                formatter.parseDateTime("17-02-2018 18:34"), "1699");

        Transaction t9 = new Transaction(BigDecimal.valueOf(4.69d),
                formatter.parseDateTime("01-02-2018 02:34"), "8488");

        Transaction t10 = new Transaction(BigDecimal.valueOf(1111.11d),
                formatter.parseDateTime("15-01-2018 21:34"), "9912");

        transactions.add(t);
        transactions.add(t2);
        transactions.add(t3);
        transactions.add(t4);
        transactions.add(t5);
        transactions.add(t6);
        transactions.add(t7);
        transactions.add(t8);
        transactions.add(t9);
        transactions.add(t10);
    }


    @Override
    public List<Transaction> search(String query) {

        BigDecimal amount;

        try{
            amount = BigDecimal.valueOf(Double.valueOf(query));
        }catch (NumberFormatException nfe) {
           amount = null;
        }

        DateTime dateTime;

        try{
            dateTime = formatter.parseDateTime(query);
        }catch (IllegalArgumentException iae) {
            try{
               dateTime = shortFormat.parseLocalDate(query).toDateTime(LocalTime.MIDNIGHT);
            }catch (IllegalArgumentException iae2) {
                dateTime = null;
            }
        }

        Transaction t = new Transaction(amount, dateTime, query);

        return transactions.stream().filter(transaction -> transaction.equals(t))
                .collect(Collectors.toList());
    }
}
