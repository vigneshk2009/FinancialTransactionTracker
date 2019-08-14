FinancialTransactionTracker:
This is a simple app to track all the financial transactions between accounts. Input to the app is a file name that will be passed as the command arguments.

Run command: java -jar financial-transaction-tracker-1.0-SNAPSHOT.jar <Absolute path of the input file>

The design of the app is that we maintain details of each account. We maintain all the deposits and withdrawals from the account in a separate lists.
When the query is made for all the transactions of a account in given time period, the app gets all the deposits and withdrawals from the account in that time frame and subtract each other. Each transaction also has a flag that indicates if the transaction was reversed. When the app encounters reversal transaction it marks the corresponding transaction as reversed and that transaction will no be processed in transaction auditing.