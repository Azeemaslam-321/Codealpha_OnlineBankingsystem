
# Online Banking System

The Online Banking System is a simple Java console application that simulates basic banking operations such as deposit, withdrawal, and account transfers. The system uses multithreading to handle parallel transfers and ensures thread safety through synchronization.

## Table Content

Table of Contents
Features
Prerequisites
Usage
Structure
Contributing
License

## Features

- Deposit: Users can deposit funds into their account.
- Withdrawal: Users can withdraw funds from their account, with    checks for insufficient funds.
- Transfer: Users can transfer funds to another account, with checks for sufficient funds and prevention of transfers to the same account.
- View Balance: Users can view their current account balance.
- View Transaction History: Users can view a list of their     transaction history.
- Parallel Transfer: Perform fund transfers between accounts in parallel using multithreading.

## Prerequisites

- Java Development Kit (JDK) installed on your machine.
- A Java IDE or a text editor for running the application.

# Clone the Online Banking System repository to your local machine using the following command-

# Clone the repository

git clone <https://github.com/Azeemaslam-321/Codealpha_OnlineBankingsystem.git>

# Navigate to the project directory

cd online-banking-system

# Compile the Java classes

java OnlineBankingSystem.java

# Run the OnlineBankingSystem class

java OnlineBankingSystem

# Example Interaction-

1. Deposit
2. Withdraw
3. Transfer
4. View Balance
5. View Transaction History
6. Exit
7. Parallel Transfer

Select an option: 1

Enter deposit amount: $500
Deposit successful. New balance: $1500.0

Select an option: 3

Enter recipient account number: 789012
Enter transfer amount: $200
Transfer successful. New balance: $1300.0

Select an option: 4

Current Balance: $1300.0

Select an option: 5

Transaction History:
Deposit: +$500.0
Transfer to 789012: -$200.0

Select an option: 6

Exiting the system. Goodbye!

## Structure

he project is organized as follows:

- BankAccount Class: Represents a bank account with methods for deposit, withdrawal, and transfer. Implements synchronization for thread safety.

- OnlineBankingSystem Class: The main class that interacts with the user. Handles user input, account operations, and parallel transfers.

- TransactionTask Class: Implements the Callable interface for parallel fund transfers.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please create an issue or submit a pull request.

## License

This project is licensed under the MIT License.
