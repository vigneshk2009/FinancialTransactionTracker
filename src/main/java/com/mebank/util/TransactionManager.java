package com.mebank.util;

import com.mebank.model.Transaction;
import com.mebank.model.TransactionResponse;

import java.util.Date;

public interface TransactionManager {

    void processTransaction(Transaction transaction);
    void reverseTransaction(Transaction transaction);
    TransactionResponse getBalance(String accountId, Date fromDate, Date toDate);

}
