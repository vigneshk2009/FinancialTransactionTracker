package com.mebank.model;

import java.math.BigDecimal;
import java.util.Date;

public class Deposit {

    private String tranasactionId;
    private String fromAccountId;
    private BigDecimal ammount;
    private Date transactionDate;
    private boolean reversed;

    public String getTranasactionId() {
        return tranasactionId;
    }

    public String getFromAccountId() {
        return fromAccountId;
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

    public Deposit(String tranasactionId, String fromAccountId, BigDecimal ammount, Date transactionDate, boolean reversed) {
        this.tranasactionId = tranasactionId;
        this.fromAccountId = fromAccountId;
        this.ammount = ammount;
        this.transactionDate = transactionDate;
        this.reversed = reversed;
    }




}
