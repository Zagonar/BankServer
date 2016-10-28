/*
 * Executes the commands of the Simple Bank Access Protocol with a Socket
 */
package bankserver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;
/**
 *
 * @author massimiliano
 */
public class BankService extends Thread {
    /**
     * Builds an object that analyzes commands sent to a bank by a socket
     * @param aSocket the socket
     * @param aBank the bank
     */
    public BankService(Socket aSocket, Bank aBank)
    {
        s = aSocket;
        bank = aBank;
    }
    /**
    Executes the commands until the QUIT command or the end of the input stream
    */
    public void run() 
    {
        try {
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream());
        
        while (true)
        {
            String line = in.readLine();
            System.out.println("Received: " + line);
            if (line == null || line.equals("QUIT"))
                return;
            
            String response = executeCommand(line);
            
            System.out.println("Sending " + response);
            out.println(response);
            out.flush();
        }
        }
        catch (IOException e)
        {
            System.out.println("input / output stream error");
        }
    }
    /**
     * Executes a single command
     * @param line the command
     * @return the response to sent to the client
     */
    public String executeCommand(String line)
    {
        StringTokenizer tokenizer = new StringTokenizer(line);
        String command = tokenizer.nextToken();
        int account = Integer.parseInt(tokenizer.nextToken());
        if (command.equals("DEPOSIT"))
        {
            double amount = Double.parseDouble(tokenizer.nextToken());
            bank.deposit(account, amount);
        }
        else if(command.equals("WITHDRAW"))
        {
            double amount = Double.parseDouble(tokenizer.nextToken());
            bank.withdraw(account, amount);
        }
        else if(!command.equals("BALANCE"))
            return "Invalid command";
        
        return account + " " + bank.getBalance(account);
    }
    
    private Socket s;
    private Bank bank;
}
