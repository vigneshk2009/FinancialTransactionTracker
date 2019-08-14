package com.mebank.model;

import java.math.BigDecimal;

public class TransactionResponse {

    private BigDecimal amount;
    private Integer count;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public TransactionResponse(BigDecimal amount, Integer count) {
        this.amount = amount;
        this.count = count;
    }
}
