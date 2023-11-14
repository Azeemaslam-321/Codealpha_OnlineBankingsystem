class OnlineBankingSystem {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);

//         // Create a map to store accounts
//         Map<String, BankAccount> accounts = new HashMap<>();
//         accounts.put("123456", new BankAccount("123456", "John Doe", 1000.0));
//         accounts.put("789012", new BankAccount("789012", "Jane Doe", 500.0));

//         ExecutorService executorService = Executors.newFixedThreadPool(5);

//         while (true) {
//             System.out.println("1. Deposit");
//             System.out.println("2. Withdraw");
//             System.out.println("3. Transfer");
//             System.out.println("4. View Balance");
//             System.out.println("5. View Transaction History");
//             System.out.println("6. Exit");
//             System.out.println("7. Parallel Transfer");

//             System.out.print("Select an option: ");
//             int choice = scanner.nextInt();

//             switch (choice) {
//                 case 1:
//                     System.out.print("Enter deposit amount: $");
//                     double depositAmount = readAmount(scanner);
//                     accounts.get("123456").deposit(depositAmount);
//                     System.out.println("Deposit successful. New balance: $" + accounts.get("123456").getBalance());
//                     break;

//                 case 2:
//                     System.out.print("Enter withdrawal amount: $");
//                     double withdrawalAmount = readAmount(scanner);
//                     accounts.get("123456").withdraw(withdrawalAmount);
//                     System.out.println("Withdrawal successful. New balance: $" + accounts.get("123456").getBalance());
//                     break;

//                 case 3:
//                     System.out.print("Enter recipient account number: ");
//                     String recipientAccountNumber = scanner.next();
//                     BankAccount recipientAccount = accounts.get(recipientAccountNumber);

//                     if (recipientAccount != null) {
//                         System.out.print("Enter transfer amount: $");
//                         double transferAmount = readAmount(scanner);
//                         accounts.get("123456").transfer(recipientAccount, transferAmount);
//                         System.out.println("Transfer successful. New balance: $" + accounts.get("123456").getBalance());
//                     } else {
//                         System.out.println("Recipient account not found!");
//                     }
//                     break;

//                 case 4:
//                     System.out.println("Current Balance: $" + accounts.get("123456").getBalance());
//                     break;

//                 case 5:
//                     accounts.get("123456").getTransactionHistory().forEach(System.out::println);
//                     break;

//                 case 6:
//                     System.out.println("Exiting the system. Goodbye!");
//                     executorService.shutdown();
//                     System.exit(0);
//                     break;

//                 case 7:
//                     System.out.print("Enter recipient account number: ");
//                     String parallelRecipientAccountNumber = scanner.next();
//                     BankAccount parallelRecipientAccount = accounts.get(parallelRecipientAccountNumber);

//                     if (parallelRecipientAccount != null) {
//                         System.out.print("Enter transfer amount: $");
//                         double parallelTransferAmount = readAmount(scanner);
//                         Callable<String> transactionTask = new TransactionTask(accounts.get("123456"), parallelRecipientAccount, parallelTransferAmount);
//                         Future<String> future = executorService.submit(transactionTask);

//                         try {
//                             String result = future.get();
//                             System.out.println(result);
//                             System.out.println("New balance: $" + accounts.get("123456").getBalance());
//                         } catch (InterruptedException | ExecutionException e) {
//                             e.printStackTrace();
//                         }
//                     } else {
//                         System.out.println("Recipient account not found!");
//                     }
//                     break;

//                 default:
//                     System.out.println("Invalid choice. Please try again.");
//             }
//         }
//     }

//     private static double readAmount(Scanner scanner) {
//         while (true) {
//             try {
//                 return Double.parseDouble(scanner.next());
//             } catch (NumberFormatException e) {
//                 System.out.println("Invalid input. Please enter a numeric value.");
//             }
//         }
//     }
// }