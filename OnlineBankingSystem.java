import java.util.*;
import java.util.concurrent.*;

class OnlineBankingSystem {
    private static final String EXIT_COMMAND = "exit";
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, BankAccount> accounts = new HashMap<>();
    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);

    static {
      
        accounts.put("123456", new BankAccount("123456", "John Doe", 1000.0));
        accounts.put("789012", new BankAccount("789012", "Jane Doe", 500.0));
    }

    public static void main(String[] args) {
        while (true) {
            printMenu();

            System.out.print("Select an option: ");
            String choice = scanner.next().toLowerCase();

            switch (choice) {
                case "1":
                    handleDeposit();
                    break;

                case "2":
                    handleWithdrawal();
                    break;

                case "3":
                    handleTransfer();
                    break;

                case "4":
                    viewBalance();
                    break;

                case "5":
                    viewTransactionHistory();
                    break;

                case "6":
                    exitSystem();
                    break;

                case "7":
                    handleParallelTransfer();
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Transfer");
        System.out.println("4. View Balance");
        System.out.println("5. View Transaction History");
        System.out.println("6. Exit");
        System.out.println("7. Parallel Transfer");
    }

    private static double readAmount() {
        while (true) {
            try {
                System.out.print("Enter amount: $");
                return Double.parseDouble(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }
    }

    private static void handleDeposit() {
        String accountNumber = "123456";
        BankAccount userAccount = accounts.get(accountNumber);

        if (userAccount != null) {
            double depositAmount = readAmount();
            userAccount.deposit(depositAmount);
            System.out.println("Deposit successful. New balance: $" + userAccount.getBalance());
        } else {
            System.out.println("Account not found!");
        }
    }

    private static void handleWithdrawal() {
        String accountNumber = "123456";
        BankAccount userAccount = accounts.get(accountNumber);

        if (userAccount != null) {
            double withdrawalAmount = readAmount();
            userAccount.withdraw(withdrawalAmount);
            System.out.println("Withdrawal successful. New balance: $" + userAccount.getBalance());
        } else {
            System.out.println("Account not found!");
        }
    }

    private static void handleTransfer() {
        String sourceAccountNumber = "123456";
        BankAccount sourceAccount = accounts.get(sourceAccountNumber);

        if (sourceAccount != null) {
            System.out.print("Enter recipient account number: ");
            String recipientAccountNumber = scanner.next();
            BankAccount recipientAccount = accounts.get(recipientAccountNumber);

            if (recipientAccount != null) {
                double transferAmount = readAmount();
                sourceAccount.transfer(recipientAccount, transferAmount);
                System.out.println("Transfer successful. New balance: $" + sourceAccount.getBalance());
            } else {
                System.out.println("Recipient account not found!");
            }
        } else {
            System.out.println("Source account not found!");
        }
    }

    private static void viewBalance() {
        String accountNumber = "123456";
        BankAccount userAccount = accounts.get(accountNumber);

        if (userAccount != null) {
            System.out.println("Current Balance: $" + userAccount.getBalance());
        } else {
            System.out.println("Account not found!");
        }
    }

    private static void viewTransactionHistory() {
        String accountNumber = "123456";
        BankAccount userAccount = accounts.get(accountNumber);

        if (userAccount != null) {
            userAccount.getTransactionHistory().forEach(System.out::println);
        } else {
            System.out.println("Account not found!");
        }
    }

    private static void exitSystem() {
        System.out.println("Exiting the system. Goodbye!");
        executorService.shutdown();
        System.exit(0);
    }

    private static void handleParallelTransfer() {
        String sourceAccountNumber = "123456";
        BankAccount sourceAccount = accounts.get(sourceAccountNumber);

        if (sourceAccount != null) {
            System.out.print("Enter recipient account number: ");
            String recipientAccountNumber = scanner.next();
            BankAccount recipientAccount = accounts.get(recipientAccountNumber);

            if (recipientAccount != null) {
                double parallelTransferAmount = readAmount();
                Callable<String> transactionTask = new TransactionTask(sourceAccount, recipientAccount, parallelTransferAmount);
                Future<String> future = executorService.submit(transactionTask);

                try {
                    String result = future.get();
                    System.out.println(result);
                    System.out.println("New balance: $" + sourceAccount.getBalance());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Recipient account not found!");
            }
        } else {
            System.out.println("Source account not found!");
        }
    }
}

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
