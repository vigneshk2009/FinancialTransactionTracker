package com.mebank.model;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {

    private String id;
    private String fromAccountId;
    private String toAccountId;
    private Date date;
    private BigDecimal ammount;
    private TransactionType type;
    private String reversalTransactionId;

    public String getId() {
        return id;
    }

    public String getFromAccountId() {
        return fromAccountId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public Date getDate() {
        return date;
    }

    public BigDecimal getAmmount() {
        return ammount;
    }

    public TransactionType getType() {
        return type;
    }

    public String getReversalTransactionId() {
        return reversalTransactionId;
    }

    public void setReversalTransactionId(String reversalTransactionId) {
        this.reversalTransactionId = reversalTransactionId;
    }

    public Transaction(String id, String fromAccountId, String toAccountId, Date date, BigDecimal ammount, TransactionType type, String reversalTransactionId) {
        this.id = id;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.date = date;
        this.ammount = ammount;
        this.type = type;
        this.reversalTransactionId = reversalTransactionId;
    }
}
