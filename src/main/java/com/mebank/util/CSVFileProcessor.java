package com.mebank.util;

import com.mebank.model.Transaction;
import com.mebank.model.TransactionBuilder;
import com.mebank.model.TransactionType;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CSVFileProcessor implements FileProcessor {

    public Transaction convertCSVToTransaction(String transactionCSV) {
        String[] transactionArray = transactionCSV.split(",");
        Transaction transaction = null;

        if (transactionArray.length < 6) {
            return null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss", Locale.ENGLISH);
        TransactionType type = "REVERSAL".equalsIgnoreCase(transactionArray[5].trim()) ? TransactionType.REVERSAL
                :  TransactionType.PAYMENT;
        try {
            transaction = new TransactionBuilder().setId(transactionArray[0].trim())
                    .setFromAccountId(transactionArray[1].trim()).setToAccountId(transactionArray[2].trim())
                    .setDate(formatter.parse(transactionArray[3].trim()))
                    .setAmmount(new BigDecimal(transactionArray[4].trim()))
                    .setType(type)
                    .createTransaction();

            if (type == TransactionType.REVERSAL) {
                transaction.setReversalTransactionId(transactionArray[6].trim());
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return transaction;
    }

    @Override
    public List<Transaction> processFileData(String fileName) throws IOException {
        if (!new File(fileName).exists()) {
            throw new FileNotFoundException("File does not exsits");
        }

        List<Transaction> transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String newLine;
            while ((newLine = reader.readLine()) != null) {
                Transaction transaction = convertCSVToTransaction(newLine);
                if (transaction != null) {
                    transactions.add(transaction);
                }
            }
        }

        return transactions;
    }
}
