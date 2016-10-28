/*
 * A server that executes the "Simple Bank Access Protocol"
 */
package bankserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author massimiliano
 */
public class BankServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        final int ACCOUNTS_LENGTH = 10;
        Bank bank = new Bank(ACCOUNTS_LENGTH);
        final int SBAP_PORT = 8888;
        ServerSocket server = new ServerSocket (SBAP_PORT);
        System.out.println("Waiting for clients to connect...");
        
        while (true)
        {
            Socket s = server.accept();
            Thread service = new BankService(s, bank);
            service.start();
            s.close();
        }
    }
    
}
