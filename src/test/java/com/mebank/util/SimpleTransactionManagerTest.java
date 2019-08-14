package com.mebank.util;


import com.mebank.model.Transaction;
import com.mebank.model.TransactionResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class SimpleTransactionManagerTest {

    private SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss", Locale.ENGLISH);

    private TransactionManager transactionManager;

    private CSVFileProcessor csvFileProcessor = new CSVFileProcessor();


    @Test
    public void testInvalidAccount() throws ParseException {
        transactionManager = new SimpleTransactionManager();
        Transaction transaction =
                csvFileProcessor.convertCSVToTransaction("TX10001, ACC334455, ACC778899, " +
                        "20/10/2018 12:47:55, 25.00, PAYMENT");
        transactionManager.processTransaction(transaction);
        TransactionResponse response =
                transactionManager.getBalance("ACC445566", formatter.parse("20/10/2018 12:00:00"),
                        formatter.parse("20/10/2018 13:00:00"));
        assertTrue(null == response);
    }

    @Test
    public void testSimpleTransactionProcessor() throws ParseException {
        transactionManager = new SimpleTransactionManager();
        Transaction transaction =
                csvFileProcessor.convertCSVToTransaction("TX10001, ACC334455, ACC778899, " +
                        "20/10/2018 12:47:55, 25.00, PAYMENT");

        transactionManager.processTransaction(transaction);
        TransactionResponse response =
                transactionManager.getBalance("ACC334455", formatter.parse("20/10/2018 12:00:00"),
                        formatter.parse("20/10/2018 13:00:00"));
        assertTrue(null != response);
        assertTrue(response.getAmount().doubleValue() == -25.00);
    }


    @Test
    public void testDepositScenario() throws ParseException {
        transactionManager = new SimpleTransactionManager();
        Transaction transaction =
                csvFileProcessor.convertCSVToTransaction("TX10001, ACC334455, ACC778899, " +
                        "20/10/2018 12:47:55, 25.00, PAYMENT");

        transactionManager.processTransaction(transaction);
        TransactionResponse response =
                transactionManager.getBalance("ACC778899", formatter.parse("20/10/2018 12:00:00"),
                        formatter.parse("20/10/2018 13:00:00"));
        assertTrue(null != response);
        assertTrue(response.getAmount().doubleValue() == 25.00);
    }

    @Test
    public void testMultipleDeposit() throws ParseException {
        transactionManager = new SimpleTransactionManager();
        Transaction transaction =
                csvFileProcessor.convertCSVToTransaction("TX10001, ACC334455, ACC778899, " +
                        "20/10/2018 12:47:55, 25.00, PAYMENT");

        transactionManager.processTransaction(transaction);

        transaction =
                csvFileProcessor.convertCSVToTransaction("TX10002, ACC998877, ACC778899, " +
                        "20/10/2018 12:47:55, 15.00, PAYMENT");

        transactionManager.processTransaction(transaction);

        TransactionResponse response =
                transactionManager.getBalance("ACC778899", formatter.parse("20/10/2018 12:00:00"),
                        formatter.parse("20/10/2018 13:00:00"));
        assertTrue(null != response);
        assertTrue(response.getAmount().doubleValue() == 40.00);
    }

    @Test
    public void testMultipleWithdrawals() throws ParseException {
        transactionManager = new SimpleTransactionManager();
        Transaction transaction =
                csvFileProcessor.convertCSVToTransaction("TX10001, ACC334455, ACC778899, " +
                        "20/10/2018 12:47:55, 25.00, PAYMENT");

        transactionManager.processTransaction(transaction);

        transaction =
                csvFileProcessor.convertCSVToTransaction("TX10002, ACC334455, ACC778899, " +
                        "20/10/2018 12:47:55, 15.00, PAYMENT");

        transactionManager.processTransaction(transaction);

        TransactionResponse response =
                transactionManager.getBalance("ACC334455", formatter.parse("20/10/2018 12:00:00"),
                        formatter.parse("20/10/2018 13:00:00"));
        assertTrue(null != response);
        assertTrue(response.getAmount().doubleValue() == -40.00);
    }

    @Test
    public void testCombinationTransactions() throws ParseException {
        transactionManager = new SimpleTransactionManager();
        Transaction transaction =
                csvFileProcessor.convertCSVToTransaction("TX10001, ACC334455, ACC778899, " +
                        "20/10/2018 12:47:55, 25.00, PAYMENT");

        transactionManager.processTransaction(transaction);

        transaction =
                csvFileProcessor.convertCSVToTransaction("TX10002, ACC778899, ACC334455, " +
                        "20/10/2018 12:47:55, 15.00, PAYMENT");

        transactionManager.processTransaction(transaction);

        TransactionResponse response =
                transactionManager.getBalance("ACC334455", formatter.parse("20/10/2018 12:00:00"),
                        formatter.parse("20/10/2018 13:00:00"));
        assertTrue(null != response);
        assertTrue(response.getAmount().doubleValue() == -10.00);
    }


    @Test
    public void testReversalTransactions() throws ParseException {
        transactionManager = new SimpleTransactionManager();
        Transaction transaction =
                csvFileProcessor.convertCSVToTransaction("TX10002, ACC334455, ACC998877, " +
                        "20/10/2018 17:33:43, 10.50, PAYMENT");

        transactionManager.processTransaction(transaction);

        transaction =
                csvFileProcessor.convertCSVToTransaction("TX10004, ACC334455, ACC998877, " +
                        "20/10/2018 19:45:00, 10.50, REVERSAL, TX10002");

        transactionManager.reverseTransaction(transaction);

        TransactionResponse response =
                transactionManager.getBalance("ACC334455", formatter.parse("20/10/2018 12:00:00"),
                        formatter.parse("20/10/2018 13:00:00"));
        assertTrue(null != response);
        assertTrue(response.getAmount().doubleValue() == 0.00);
        assertTrue(response.getCount() == 0);
    }
}
