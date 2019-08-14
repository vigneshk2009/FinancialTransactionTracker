package com.mebank.model;

import java.math.BigDecimal;
import java.util.Date;

public class WithdrawalBuilder {
    private String transactionId;
    private String toAccountId;
    private BigDecimal ammount;
    private Date transactionDate;
    private boolean reversed;

    public WithdrawalBuilder setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public WithdrawalBuilder setToAccountId(String toAccountId) {
        this.toAccountId = toAccountId;
        return this;
    }

    public WithdrawalBuilder setAmmount(BigDecimal ammount) {
        this.ammount = ammount;
        return this;
    }

    public WithdrawalBuilder setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
        return this;
    }

    public WithdrawalBuilder setReversed(boolean reversed) {
        this.reversed = reversed;
        return this;
    }

    public Withdrawal createWithdrawal() {
        return new Withdrawal(transactionId, toAccountId, ammount, transactionDate, reversed);
    }
}