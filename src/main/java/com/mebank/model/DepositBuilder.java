package com.mebank.model;

import java.math.BigDecimal;
import java.util.Date;

public class DepositBuilder {
    private String tranasactionId;
    private String fromAccountId;
    private BigDecimal ammount;
    private Date transactionDate;
    private boolean reversed;

    public DepositBuilder setTranasactionId(String tranasactionId) {
        this.tranasactionId = tranasactionId;
        return this;
    }

    public DepositBuilder setFromAccountId(String fromAccountId) {
        this.fromAccountId = fromAccountId;
        return this;
    }

    public DepositBuilder setAmmount(BigDecimal ammount) {
        this.ammount = ammount;
        return this;
    }

    public DepositBuilder setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
        return this;
    }

    public DepositBuilder setReversed(boolean reversed) {
        this.reversed = reversed;
        return this;
    }

    public Deposit createDeposit() {
        return new Deposit(tranasactionId, fromAccountId, ammount, transactionDate, reversed);
    }
}