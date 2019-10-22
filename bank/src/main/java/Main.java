import ie.stephen.service.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import ie.stephen.service.CustomerService;

import javax.sql.DataSource;
import java.util.Scanner;

/*
Author:         Stephen O Driscoll
Student Number: R00146853
 */

public class Main {

    private static Scanner kb = new Scanner(System.in);
    private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    private static CustomerService customerService = (CustomerService) context.getBean("customerServiceImplementation");
    private static AccountService accountService = (AccountService) context.getBean("accountServiceImplementation");

    public static void main(String[] args) {
        boolean exit = false;
        int input;

        while (!exit) {
            input = getPositiveInt(
                    "Choose an option (1-9):\n" +
                    "\t1. Register\n" +
                    "\t2. Create account\n" +
                    "\t3. Add a person to an account\n" +
                    "\t4. View accounts\n" +
                    "\t5. Make a withdrawal\n" +
                    "\t6. Make a deposit\n" +
                    "\t7. Transfer money\n" +
                    "\t8. Close an account\n" +
                    "\t9. Exit\n\n" +
                    "\t10. Total Deposited\n" +
                    "\t11. Num accounts over €10,000\n");

            switch (input) {
                case 1:
                    register();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    addPersonToAccount();
                    break;
                case 4:
                    viewAccounts();
                    break;
                case 5:
                    withdraw();
                    break;
                case 6:
                    deposit();
                    break;
                case 7:
                    transferMoney();
                    break;
                case 8:
                    closeAccount();
                    break;
                case 9:
                    exit = true;
                    break;
                case 10:
                    totalDeposited();
                    break;
                case 11:
                    numAboveLimit(10000);
                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }

            getString("Press enter to continue");
        }
    }

    private static void register() {
        String firstName = getString("Enter first Name: ");
        String lastName = getString("Enter last Name: ");
        customerService.newCustomer(firstName, lastName);
    }

    private static void createAccount() {
        int customerId = getPositiveInt("Enter customer id: ");
        double overdraftLimit = getPositiveDouble("Enter overdraft limit: ");
        if (customerService.exists(customerId)) {
            accountService.createAccount(customerId, overdraftLimit);
        }
    }

    private static void addPersonToAccount() {
        int accountId = getPositiveInt("Enter account id: ");
        int customerId = getPositiveInt("Enter id of customer to add: ");
        if (customerService.exists(customerId)) {
            accountService.addPersonToAccount(accountId, customerId);
        }
    }

    private static void viewAccounts() {
        int customerId = getPositiveInt("Enter customer id: ");
        if (customerService.displayInfo(customerId)) {
            accountService.viewAccounts(customerId);
        }
    }

    private static void withdraw() {
        int accountId = getPositiveInt("Enter account id: ");
        double amount = getPositiveDouble("Enter amount to withdraw: ");
        accountService.withdraw(accountId, amount);
    }

    private static void deposit() {
        int accountId = getPositiveInt("Enter account id: ");
        double amount = getPositiveDouble("Enter amount to deposit: ");
        accountService.deposit(accountId, amount);
    }

    private static void transferMoney() {
        int account1Id = getPositiveInt("Enter your account id: ");
        int account2Id = getPositiveInt("Enter account id to transfer to: ");
        double amount = getPositiveDouble("Enter amount to transfer: ");
        accountService.transfer(account1Id, account2Id, amount);
    }

    private static void closeAccount() {
        int accountId = getPositiveInt("Enter account id: ");
        accountService.closeAccount(accountId);
    }

    private static void totalDeposited() {
        accountService.totalDeposited();
    }

    private static void numAboveLimit(int limit) {
        accountService.numAboveLimit(limit);
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("testDB")
                .addScript("classpath:schema.sql")
                .addScript("classpath:data.sql").build();
    }

    private static String getString(String question) {
        System.out.println(question);
        return kb.nextLine();
    }

    private static int getPositiveInt(String question) {
        int input;

        while (true) {
            System.out.println(question);

            if (!kb.hasNextInt() || (input = kb.nextInt()) < 0) {
                kb.nextLine();
                System.out.println("Please enter a positive whole number");
            }
            else {
                kb.nextLine();
                return input;
            }
        }
    }

    private static double getPositiveDouble(String question) {
        double input;

        while (true) {
            System.out.println(question);

            if (!kb.hasNextDouble() || (input = kb.nextDouble()) < 0) {
                kb.nextLine();
                System.out.println("Please enter a positive number");
            }
            else {
                kb.nextLine();
                return input;
            }
        }
    }
}
