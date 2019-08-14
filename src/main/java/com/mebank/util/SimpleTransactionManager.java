package com.mebank.util;

import com.mebank.model.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class SimpleTransactionManager implements TransactionManager {


    private Map<String, Account> accountDetails = new HashMap<>();

    public void processTransaction(Transaction transaction) {
        processDeposit(transaction);
        processWithdrawal(transaction);
    }

    public void reverseTransaction(Transaction transaction) {
        processWithdrawalReversal(transaction);
        processDepositReversal(transaction);
    }

    public TransactionResponse getBalance(String accountId, final Date fromDate, final Date toDate) {
        final Account account = accountDetails.get(accountId);
        if (null != account) {
            final List<Deposit> deposits = new ArrayList<>();
            account.getDeposits().keySet().forEach(txnId -> {
                Deposit deposit = account.getDeposits().get(txnId);
                if (deposit.getTransactionDate().after(fromDate) &&
                        deposit.getTransactionDate().before(toDate) && !deposit.isReversed()) {
                    deposits.add(deposit);
                }
            });
            double deposit = deposits.stream().mapToDouble(dep -> {
                return dep.getAmmount().doubleValue();
            }).sum();

            final List<Withdrawal> withdrawals = new ArrayList<>();
            account.getWithdrawals().keySet().forEach(txnId -> {
                Withdrawal withdrawal = account.getWithdrawals().get(txnId);
                if (withdrawal.getTransactionDate().after(fromDate) &&
                        withdrawal.getTransactionDate().before(toDate) && !withdrawal.isReversed()) {
                    withdrawals.add(withdrawal);
                }
            });

            double withdrawal = withdrawals.stream().mapToDouble(withd -> {
                return withd.getAmmount().doubleValue();
            }).sum();

            return new TransactionResponse(BigDecimal.valueOf(deposit - withdrawal),
                    deposits.size() + withdrawals.size());
        }

        return null;
    }

    private void processWithdrawal(Transaction transaction) {
        Account account = createOrFetchAccount(transaction.getFromAccountId());
        if (account != null) {
            Withdrawal withdrawal = new WithdrawalBuilder().setToAccountId(transaction.getToAccountId())
                    .setAmmount(transaction.getAmmount()).setTransactionDate(transaction.getDate()).setReversed(false)
                    .setTransactionId(transaction.getId()).createWithdrawal();
            account.getWithdrawals().put(transaction.getId(), withdrawal);
        }
    }

    private void processDeposit(Transaction transaction) {
        Account account = createOrFetchAccount(transaction.getToAccountId());
        if (account != null) {
            Deposit deposit = new DepositBuilder().setFromAccountId(transaction.getFromAccountId())
                    .setAmmount(transaction.getAmmount()).setReversed(false).setTransactionDate(transaction.getDate())
                    .setTranasactionId(transaction.getId()).createDeposit();
            account.getDeposits().put(transaction.getId(), deposit);
        }
    }

    private void processWithdrawalReversal(Transaction transaction) {
        Account account = accountDetails.get(transaction.getFromAccountId());
        if (account != null) {
            Withdrawal withdrawal = account.getWithdrawals().get(transaction.getReversalTransactionId());
            if(null != withdrawal) {
                withdrawal.setReversed(true);
            }
        }
    }

    private void processDepositReversal(Transaction transaction) {
        Account account = accountDetails.get(transaction.getToAccountId());
        if (null != account) {
            Deposit deposit = account.getDeposits().get(transaction.getReversalTransactionId());
            if (null != deposit) {
                deposit.setReversed(true);
            }
        }
    }

    private Account createOrFetchAccount(String accountId) {
        if (accountDetails.containsKey(accountId)) {
            return accountDetails.get(accountId);
        }

        Account account = new AccountBuilder().setId(accountId).createAccount();
        this.accountDetails.put(accountId, account);

        return account;
    }
}
