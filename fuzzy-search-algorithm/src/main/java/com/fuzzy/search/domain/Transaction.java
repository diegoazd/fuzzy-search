package com.fuzzy.search.domain;

import org.apache.commons.text.similarity.LevenshteinDetailedDistance;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
    public static final int LIMIT_DISTANCE = 1;
    private BigDecimal amount;
    private DateTime date;
    private String cardLastFour;
    private String dateTime;

    public Transaction(BigDecimal amount, DateTime date, String cardLastFour) {
        this.amount = amount;
        this.date = date;
        this.cardLastFour = cardLastFour;
        this.dateTime =  this.date != null ? this.date.toString("dd-MM-yy hh:mm") : "";
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public DateTime getDate() {
        return date;
    }

    public String getCardLastFour() {
        return cardLastFour;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    @Override
    public boolean equals(Object o) {

        if(o == null) return false;
        final Transaction t2 = (Transaction)o;

        if(isSimilarString(this.getCardLastFour(), t2.getCardLastFour())) {
            return true;
        }else if(isSimilarBigDecimal(this.amount, t2.getAmount())) {
            return true;
        }else if(isSimilarDate(this.date, t2.getDate())) {
            return true;
        }

        return false;
    }

    private boolean isSimilarString(String s, String s2) {
        final LevenshteinDetailedDistance ldd = new LevenshteinDetailedDistance();
        return (s == null || s2 == null) ? false :
                ldd.apply(s, s2).getDistance() <= LIMIT_DISTANCE;
    }

    private boolean isSimilarBigDecimal(BigDecimal amount, BigDecimal amount2) {
        return (amount == null || amount2 == null) ? false :
                isSimilarString(amount.toString(), amount2.toString());
    }

    private boolean isSimilarDate(DateTime d, DateTime d2) {
         if(d == null || d2 == null) return false;
         final int days =  Days.daysBetween(d, d2).getDays();

         return ( days >= -LIMIT_DISTANCE && days <= LIMIT_DISTANCE);
    }

}
