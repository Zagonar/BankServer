/*
 * A bank account with a balance which can be modified with a deposit 
 *or a withdraw
 */
package bankserver;

/**
 *
 * @author massimiliano
 */
public class BankAccount {
    /**
     * build a bank account with balance 0
     */
    public BankAccount()
    {
        balance = 0;
    }
    /**
     * deposit money in the account
     */
    public synchronized void deposit(double amount)
    {
        balance = balance + amount;
        notifyAll();
    }
    /**
     * withdraw money from the account
     */
    public synchronized void withdraw(double amount) throws InterruptedException
    {
        while (balance < amount)
        {
            wait();
        }
        balance = balance - amount;
    }
    /**
     * gives the current balance of the account
     */
    public double getBalance()
    {
        return balance;
    }
    
    private double balance;
}
