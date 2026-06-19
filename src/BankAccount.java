public class BankAccount {

    // Private instance variables
    private int accountNumber;
    private String accountHolderName;
    private int pin;
    private double balance;

    // Parameterized constructor
    public BankAccount(
            int accountNumber,
            String accountHolderName,
            int pin,
            double balance) {

        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.pin = pin;
        this.balance = balance;
    }

    // Login method
    public boolean login(int enteredAccountNumber, int enteredPin) {
        return accountNumber == enteredAccountNumber
                && pin == enteredPin;
    }

    // Check balance
    public void checkBalance() {
        System.out.println("Current Balance: " + balance);
    }

    // Deposit amount
    public void deposit(double amount) {

        if (amount > 0) {
            balance = balance + amount;

            System.out.println("Amount Deposited Successfully");
            System.out.println("Updated Balance: " + balance);
        } else {
            System.out.println("Invalid Deposit Amount");
        }
    }

    // Withdraw amount
    public boolean withdraw(double amount) {

        if (amount <= 0) {
            System.out.println("Invalid Withdrawal Amount");
            return false;
        }

        if (amount > balance) {
            System.out.println("Insufficient Balance");
            return false;
        }

        balance = balance - amount;

        System.out.println("Amount Withdrawn Successfully");
        System.out.println("Updated Balance: " + balance);

        return true;
    }

    // Display account details
    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder Name: " + accountHolderName);
    }

    // Return private account number
    public int getAccountNumber() {
        return accountNumber;
    }

    // Receive transferred amount
    public void receiveAmount(double amount) {

        if (amount > 0) {
            balance = balance + amount;
        }
    }

    // Transfer amount
    public void transferAmount(
            BankAccount receiver,
            double amount) {

        if (amount <= 0) {
            System.out.println("Invalid Transfer Amount");
        } else if (amount > balance) {
            System.out.println("Insufficient Balance");
        } else {
            balance = balance - amount;
            receiver.receiveAmount(amount);

            System.out.println("Transfer Successful");
            System.out.println("Amount Transferred: " + amount);
            System.out.println("Updated Balance: " + balance);
        }
    }
}