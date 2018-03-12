package com.fuzzy.search.domain;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class TransactionTest {

    Transaction transaction;
    DateTime date;
    BigDecimal amount;
    String cardLastFour;
    DateTimeFormatter formatter;

    @Before
    public void setUp() {
        formatter = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm");
        date = formatter.parseDateTime("27-01-2018 12:34");
        amount = BigDecimal.ZERO;
        cardLastFour = "0000";
        transaction = new Transaction(amount, date, cardLastFour);
    }

    @Test
    public void shouldCreateATransaction() {
        Assert.assertEquals(date, transaction.getDate());
        Assert.assertEquals(amount, transaction.getAmount());
        Assert.assertEquals(cardLastFour, transaction.getCardLastFour());
    }

    @Test
    public void shouldDistanceOnCardBeTrue() {
        Transaction t2 = new Transaction(null, null, "0100");
        Assert.assertTrue(transaction.equals(t2));
    }

    @Test
    public void shouldDistanceOnCardBeFalse() {
        Transaction t2 = new Transaction(null, null, "0110");
        Assert.assertFalse(transaction.equals(t2));
    }

    @Test
    public void shouldDistanceOnAmountBeTrue() {
        Transaction t2 = new Transaction(BigDecimal.TEN, null, null);
        Assert.assertTrue(transaction.equals(t2));

        Transaction t = new Transaction(BigDecimal.valueOf(100.00d), null, null);
        t2 = new Transaction(BigDecimal.valueOf(101.00d), null, null);

        Assert.assertTrue(t.equals(t2));

        t = new Transaction(BigDecimal.valueOf(100.00d), null, null);
        t2 = new Transaction(BigDecimal.valueOf(200.00d), null, null);

        Assert.assertTrue(t.equals(t2));
    }

    @Test
    public void shouldReturnFalseOnNull() {
        Transaction t2 = null;
        Assert.assertFalse(transaction.equals(t2));
    }

    @Test
    public void shouldDistanceOnAmountBeFalse() {
        Transaction t = new Transaction(BigDecimal.valueOf(100d), null, null);
        Transaction t2 = new Transaction(BigDecimal.TEN, null, null);
        Assert.assertFalse(t.equals(t2));

        t = new Transaction(BigDecimal.valueOf(100.01d), null, null);
        t2 = new Transaction(BigDecimal.valueOf(101.00d), null, null);

        Assert.assertFalse(t.equals(t2));

        t = new Transaction(BigDecimal.valueOf(100.00d), null, null);
        t2 = new Transaction(BigDecimal.valueOf(201.00d), null, null);

        Assert.assertFalse(t.equals(t2));

        t = new Transaction(null, null, null);
        t2 = new Transaction(BigDecimal.valueOf(201.00d), null, null);

        Assert.assertFalse(t.equals(t2));
    }

    @Test
    public void shouldDistanceBeFewerThanOneDay() {
        DateTime d = formatter.parseDateTime("28-01-2018 12:35");
        Transaction t2 = new Transaction(null, d, null);

        Assert.assertTrue(transaction.equals(t2));

        d = formatter.parseDateTime("26-01-2018 12:34");
        t2 = new Transaction(null, d, null);

        Assert.assertTrue(transaction.equals(t2));

        d = formatter.parseDateTime("25-01-2018 12:35");
        t2 = new Transaction(null, d, null);

        Assert.assertTrue(transaction.equals(t2));

        d = formatter.parseDateTime("29-01-2018 12:33");
        t2 = new Transaction(null, d, null);

        Assert.assertTrue(transaction.equals(t2));
    }

    @Test
    public void shouldDistanceBeGreaterThanOneDay() {
        DateTime d = formatter.parseDateTime("29-01-2018 12:35");
        Transaction t2 = new Transaction(null, d, null);

        Assert.assertFalse(transaction.equals(t2));

        d = formatter.parseDateTime("25-01-2018 12:34");
        t2 = new Transaction(null, d, null);

        Assert.assertFalse(transaction.equals(t2));
    }
}