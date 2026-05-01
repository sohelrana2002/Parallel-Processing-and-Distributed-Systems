import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    // shared list of all connected clients
    static ArrayList<ClientHandler> clientList = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(3000);
            System.out.println("Chat server is running and waiting for clients...");

            while (true) {
                Socket s = ss.accept();
                System.out.println("New client is connected.");

                ClientHandler 
            }
        } catch (Exception e) {
            System.out.println("Server error: " + e);
        }
    }
}