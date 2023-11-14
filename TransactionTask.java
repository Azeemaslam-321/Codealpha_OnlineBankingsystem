class TransactionTask implements Callable<String> {
    private BankAccount sourceAccount;
    private BankAccount destinationAccount;
    private double amount;

    public TransactionTask(BankAccount sourceAccount, BankAccount destinationAccount, double amount) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
    }

    @Override
    public String call() {
        sourceAccount.transfer(destinationAccount, amount);
        return "Transaction completed.";
    }
}