package com.mebank.util;

import com.mebank.model.Transaction;
import com.mebank.model.TransactionType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CSVFileProcessorTest {

    CSVFileProcessor fileProcessor = new CSVFileProcessor();

    @Test
    public void testInvalidInputArgs() {
        Transaction transaction = fileProcessor.convertCSVToTransaction(
                "TX10001, ACC334455, ACC778899, 20/10/2018 12:47:55, 25.00");
        assertTrue(transaction == null);

    }

    @Test
    public void testValidInputArgs() {
        Transaction transaction = fileProcessor.convertCSVToTransaction(
                "TX10001, ACC334455, ACC778899, 20/10/2018 12:47:55, 25.00, PAYMENT");
        assertTrue(transaction != null);
        assertTrue(transaction.getType() == TransactionType.PAYMENT);
    }


    @Test
    public void testReversalTransaction() {
        Transaction transaction = fileProcessor.convertCSVToTransaction(
                "TX10004, ACC334455, ACC998877, 20/10/2018 19:45:00, 10.50, REVERSAL, TX10002");
        assertTrue(transaction != null);
        assertTrue(transaction.getReversalTransactionId() != null);
        assertTrue(transaction.getType() == TransactionType.REVERSAL);
    }

}
