package com.mebank.model;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionBuilder {
    private String id;
    private String fromAccountId;
    private String toAccountId;
    private Date date;
    private BigDecimal ammount;
    private TransactionType type;
    private String reversalTransactionId;

    public TransactionBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public TransactionBuilder setFromAccountId(String fromAccountId) {
        this.fromAccountId = fromAccountId;
        return this;
    }

    public TransactionBuilder setToAccountId(String toAccountId) {
        this.toAccountId = toAccountId;
        return this;
    }

    public TransactionBuilder setDate(Date date) {
        this.date = date;
        return this;
    }

    public TransactionBuilder setAmmount(BigDecimal ammount) {
        this.ammount = ammount;
        return this;
    }

    public TransactionBuilder setType(TransactionType type) {
        this.type = type;
        return this;
    }

    public TransactionBuilder setReversalTransactionId(String reversalTransactionId) {
        this.reversalTransactionId = reversalTransactionId;
        return this;
    }

    public Transaction createTransaction() {
        return new Transaction(id, fromAccountId, toAccountId, date, ammount, type, reversalTransactionId);
    }
}