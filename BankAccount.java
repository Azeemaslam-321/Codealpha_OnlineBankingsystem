import java.util.ArrayList;
import java.util.List;

class BankAccount {
    private final String accountNumber;
    private final String accountHolder;
    private double balance;
    private final List<String> transactionHistory;
    private final Object lock = new Object();

    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }

    public void deposit(double amount) {
        synchronized (lock) {
            if (amount < 0) {
                throw new IllegalArgumentException("Deposit amount must be non-negative");
            }
            balance += amount;
            transactionHistory.add("Deposit: +$" + amount);
        }
    }

    public void withdraw(double amount) {
        synchronized (lock) {
            if (amount < 0) {
                throw new IllegalArgumentException("Withdrawal amount must be non-negative");
            }
            if (amount <= balance) {
                balance -= amount;
                transactionHistory.add("Withdrawal: -$" + amount);
            } else {
                throw new IllegalStateException("Insufficient funds for withdrawal");
            }
        }
    }

    public void transfer(BankAccount recipient, double amount) {
        synchronized (lock) {
            if (this == recipient) {
                throw new IllegalArgumentException("Cannot transfer to the same account");
            }

            if (amount < 0) {
                throw new IllegalArgumentException("Transfer amount must be non-negative");
            }

            if (amount <= balance) {
                balance -= amount;
                recipient.deposit(amount);
                transactionHistory.add("Transfer to " + recipient.getAccountNumber() + ": -$" + amount);
            } else {
                throw new IllegalStateException("Insufficient funds for transfer");
            }
        }
    }
}
