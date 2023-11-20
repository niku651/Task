import java.util.Scanner;

// BankAccount class representing the user's bank account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
            return false;
        }
    }
}

// ATM class representing the ATM machine
class ATM {
    private BankAccount bankAccount;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void displayMenu() {
        System.out.println("ATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public void processOption(int option, Scanner scanner) {
        switch (option) {
            case 1:
                checkBalance();
                break;
            case 2:
                deposit(scanner);
                break;
            case 3:
                withdraw(scanner);
                break;
            case 4:
                System.out.println("Exiting ATM. Thank you!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please choose a valid option.");
        }
    }

    private void checkBalance() {
        System.out.println("Current Balance: $" + bankAccount.getBalance());
    }

    private void deposit(Scanner scanner) {
        System.out.print("Enter deposit amount: $");
        double amount = scanner.nextDouble();
        bankAccount.deposit(amount);
        System.out.println("Deposit successful. New balance: $" + bankAccount.getBalance());
    }

    private void withdraw(Scanner scanner) {
        System.out.print("Enter withdrawal amount: $");
        double amount = scanner.nextDouble();
        if (bankAccount.withdraw(amount)) {
            System.out.println("Withdrawal successful. New balance: $" + bankAccount.getBalance());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Create a bank account with an initial balance of $1000
        BankAccount userAccount = new BankAccount(1000);

        // Create an ATM instance connected to the user's bank account
        ATM atm = new ATM(userAccount);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display the ATM menu
            atm.displayMenu();

            // Get user input for menu option
            System.out.print("Enter option (1-4): ");
            int option = scanner.nextInt();

            // Process the selected option
            atm.processOption(option, scanner);
        }
    }
}
