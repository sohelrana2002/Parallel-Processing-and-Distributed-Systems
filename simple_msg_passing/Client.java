import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 5000);
            System.out.println("Connected to the server!");

            // output to the server
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);

            // get input from server 
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            // send a message 
            out.println("Hello server! I'm from client.");

            // received reply 
            String res = in.readLine();
            System.out.println("Server says: " + res);

            s.close();
        } catch (Exception e) {
           System.err.println(e);
        }
    }
}
