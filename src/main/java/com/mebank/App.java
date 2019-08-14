package com.mebank;

import com.mebank.model.Transaction;
import com.mebank.model.TransactionResponse;
import com.mebank.model.TransactionType;
import com.mebank.util.CSVFileProcessor;
import com.mebank.util.FileProcessor;
import com.mebank.util.SimpleTransactionManager;
import com.mebank.util.TransactionManager;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class App {

    private static Scanner in = new Scanner(System.in);
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss", Locale.ENGLISH);
    public static void main(String[] args) throws IOException, ParseException {

        if (args.length <= 0) {
            System.out.println("Input file is expected but none passed.");
            return;
        }
        final String fileName = args[0];

        System.out.println("Starting transactions processor");
        System.out.println("Reading input file: " + fileName);

        FileProcessor fileProcessor = new CSVFileProcessor();
        List<Transaction> transactions = fileProcessor.processFileData(fileName);


        TransactionManager transactionManager = new SimpleTransactionManager();

        transactions.forEach(transaction -> {
            if (transaction.getType() == TransactionType.PAYMENT) {
                transactionManager.processTransaction(transaction);
            } else {
                transactionManager.reverseTransaction(transaction);
            }
        });

        for(;;) {
            System.out.println("Enter account id: ");
            String accountId = in.nextLine();
            System.out.println("Enter from: ");
            String fromDate = in.nextLine();
            System.out.println("Enter to: ");
            String toDate = in.nextLine();
            TransactionResponse response = transactionManager.getBalance(accountId, formatter.parse(fromDate), formatter.parse(toDate));
            System.out.println("Balance: " + response.getAmount());
            System.out.println("Count: " + response.getCount());
        }

    }
}
