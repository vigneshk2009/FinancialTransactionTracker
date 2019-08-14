package com.mebank.model;

import java.util.HashMap;
import java.util.Map;

public class Account {

    private String id;

    private Map<String, Deposit> deposits = new HashMap<String, Deposit>();
    private Map<String, Withdrawal> withdrawals = new HashMap<String, Withdrawal>();

    public String getId() {
        return id;
    }

    public Map<String, Deposit> getDeposits() {
        return deposits;
    }

    public Map<String, Withdrawal> getWithdrawals() {
        return withdrawals;
    }

    public Account(String id) {
        this.id = id;
    }
}
