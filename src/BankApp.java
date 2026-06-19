import java.util.Scanner;

class BankAccount {

    private int accountNumber;
    private String accountHolderName;
    private int pin;
    private double balance;

    BankAccount(int accountNumber, String accountHolderName, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.pin = pin;
        this.balance = balance;
    }

    boolean login(int enteredAccountNumber, int enteredPin) {
        if (enteredAccountNumber == accountNumber && enteredPin == pin) {
            return true;
        } else {
            return false;
        }
    }

    int getAccountNumber() {
        return accountNumber;
    }

    void checkBalance() {
        System.out.println("Current Balance: " + balance);
    }

    void deposit(double amount) {
        if (amount > 0) {
            balance = balance + amount;
            System.out.println("Amount Deposited Successfully");
            System.out.println("Updated Balance: " + balance);
        } else {
            System.out.println("Invalid Deposit Amount");
        }
    }

    void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid Withdrawal Amount");
        } else if (amount > balance) {
            System.out.println("Insufficient Balance");
        } else {
            balance = balance - amount;
            System.out.println("Amount Withdrawn Successfully");
            System.out.println("Updated Balance: " + balance);
        }
    }

    void transferAmount(BankAccount receiverAccount, double amount) {

        if (amount <= 0) {
            System.out.println("Invalid Transfer Amount");
        } else if (amount > balance) {
            System.out.println("Insufficient Balance");
        } else {
            balance = balance - amount;
            receiverAccount.balance = receiverAccount.balance + amount;

            System.out.println("Amount Transferred Successfully");
            System.out.println("Updated Balance: " + balance);
        }
    }

    void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder Name: " + accountHolderName);
    }
}

public class BankApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        BankAccount[] accounts = new BankAccount[4];

        accounts[0] =
                new BankAccount(1001, "MD AFFAN ANWAR", 1234, 5000.0);

        accounts[1] =
                new BankAccount(1002, "Priya", 2345, 8000.0);

        accounts[2] =
                new BankAccount(1003, "Amit", 3456, 3000.0);

        accounts[3] =
                new BankAccount(1004, "Sneha", 4567, 10000.0);

        System.out.println("Welcome to Bank Application");

        System.out.print("Enter Account Number: ");
        int enteredAccountNumber = scanner.nextInt();

        System.out.print("Enter PIN: ");
        int enteredPin = scanner.nextInt();

        BankAccount loggedInAccount = null;

        for (int i = 0; i < accounts.length; i++) {

            if (accounts[i].login(
                    enteredAccountNumber,
                    enteredPin)) {

                loggedInAccount = accounts[i];
                break;
            }
        }

        if (loggedInAccount == null) {
            System.out.println("Invalid Account Number or PIN");
            scanner.close();
            return;
        }

        System.out.println("Login Successful");

        int choice;

        do {
            System.out.println("\n1. Check Balance");
            System.out.println("2. Deposit Amount");
            System.out.println("3. Withdraw Amount");
            System.out.println("4. Display Account Details");
            System.out.println("5. Transfer Amount");
            System.out.println("6. Exit");

            System.out.print("\nEnter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    loggedInAccount.checkBalance();
                    break;

                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();

                    loggedInAccount.deposit(depositAmount);
                    break;

                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();

                    loggedInAccount.withdraw(withdrawalAmount);
                    break;

                case 4:
                    loggedInAccount.displayAccountDetails();
                    break;

                case 5:
                    System.out.print("Enter receiver account number: ");
                    int receiverAccountNumber = scanner.nextInt();

                    if (receiverAccountNumber
                            == loggedInAccount.getAccountNumber()) {

                        System.out.println(
                                "Cannot transfer amount to the same account");

                        break;
                    }

                    BankAccount receiverAccount = null;

                    for (int i = 0; i < accounts.length; i++) {

                        if (accounts[i].getAccountNumber()
                                == receiverAccountNumber) {

                            receiverAccount = accounts[i];
                            break;
                        }
                    }

                    if (receiverAccount == null) {
                        System.out.println("Receiver Account Not Found");
                        break;
                    }

                    System.out.print("Enter transfer amount: ");
                    double transferAmount = scanner.nextDouble();

                    loggedInAccount.transferAmount(
                            receiverAccount,
                            transferAmount);

                    break;

                case 6:
                    System.out.println(
                            "Thank you for using the Bank Application");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 6);

        scanner.close();
    }
}