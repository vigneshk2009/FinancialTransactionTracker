package com.mebank.model;

import java.math.BigDecimal;
import java.util.Date;

public class Withdrawal {

    private String transactionId;
    private String toAccountId;
    private BigDecimal ammount;
    private Date transactionDate;
    private boolean reversed;


    public String getTransactionId() {
        return transactionId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public BigDecimal getAmmount() {
        return ammount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public boolean isReversed() {
        return reversed;
    }

    public void setReversed(boolean reversed) {
        this.reversed = reversed;
    }

    public Withdrawal(String transactionId, String toAccountId, BigDecimal ammount, Date transactionDate, boolean reversed) {
        this.transactionId = transactionId;
        this.toAccountId = toAccountId;
        this.ammount = ammount;
        this.transactionDate = transactionDate;
        this.reversed = reversed;
    }
}
