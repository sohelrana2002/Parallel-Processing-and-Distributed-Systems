import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Server{
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(5000);
            System.out.println("Server is running!");

            Socket s = ss.accept();
            System.out.println("Client connected!");

            // take input from client 
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            // provide output into client 
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);

            // read messages from client 
            String msg = in.readLine();
            System.out.println("Client says: " + msg);

            // reply to the client 
            out.println("Hello client. I received your message: " + msg);

            ss.close();
            s.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}