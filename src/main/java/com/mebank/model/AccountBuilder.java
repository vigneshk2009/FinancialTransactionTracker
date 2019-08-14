package com.mebank.model;

public class AccountBuilder {
    private String id;

    public AccountBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public Account createAccount() {
        return new Account(id);
    }
}