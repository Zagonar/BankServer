/*
 * This program is made to test the bank service
 */
package bankserver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
/**
 *
 * @author massimiliano
 */
public class BankClient {
    public static void main(String[] args) throws IOException
    {
        final int SBAP_PORT = 8888;
        Socket s = new Socket("localhost", SBAP_PORT);
        InputStream in = s.getInputStream();
        OutputStream out = s.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        PrintWriter writer = new PrintWriter (out);
        
        String command = "DEPOSIT 3 1000\n";
        System.out.println("Sending: " + command);
        writer.print(command);
        writer.flush();
        String response = reader.readLine();
        System.out.println("Receiving: " + response);
        
        command = "WITHDRAW 3 500\n";
        System.out.println("Sending: " + command);
        writer.print(command);
        writer.flush();
        response = reader.readLine();
        System.out.println("Receiving: " + response);
        
        command = "QUIT\n";
        System.out.println("Sending: " + command);
        writer.print(command);
        writer.flush();
        
        s.close();
    }
}
