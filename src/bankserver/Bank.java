/*
 *Bank with multiple bank accounts
 */
package bankserver;

/**
 *
 * @author massimiliano
 */
public class Bank {
    /**
     * build a bank with a set number of accounts
     * @param size number of accounts
     */
    public Bank(int size)
    {
        accounts = new BankAccount[size];
        for (int i = 0; i < accounts.length; i++)
            accounts[i] = new BankAccount();
    }
    /**
     * deposti money in a bank account
     * @param accountNumber the account number
     * @param amount the amount to deposit
     */
    public void deposit(int accountNumber, double amount)
    {
        BankAccount account = accounts[accountNumber];
        account.deposit(amount);
    }
    /**
     * withdraw money from a bank account
     * @param accountNumber the account number
     * @param amount the amount to withdraw
     */
    public void withdraw(int accountNumber, double amount)
    {
        BankAccount account = accounts[accountNumber];
        try {
            account.withdraw(amount);
        }
        catch (InterruptedException e)
        {
            
        }
    }
    /**
     * return the balance of the bank account
     * @param accountNumber the account number
     * @return the account's balance
     */
    public double getBalance(int accountNumber)
    {
        BankAccount account = accounts[accountNumber];
        return account.getBalance();
    }
    
    private BankAccount[] accounts;
}
