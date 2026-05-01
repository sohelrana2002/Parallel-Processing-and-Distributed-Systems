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

                ClientHandler ch = new ClientHandler(s);
                clientList.add(ch);
                System.out.println("Total clients: " + Server.clientList.size());

                new Thread(ch).start();
            }
        } catch (Exception e) {
            System.out.println("Server error: " + e);
        }
    }
}

class ClientHandler implements Runnable {
    Socket socket;
    DataInputStream din;
    DataOutputStream dout;
    String name;

    public ClientHandler(Socket socket) {
        this.socket = socket;

        try {
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());

            // first message is client name 
            name = din.readUTF();
            System.out.println(name + " is connected.");

            broadCast(name + " joined the chatroom", this);
        } catch (Exception e) {
            System.out.println("ClientHandler error: " + e);
        }
    }

    @Override
    public void run() {
        String msg;

        try {
            while (true) {
                msg = din.readUTF();

                if (msg.equalsIgnoreCase("exit")) {
                    System.out.println(name + " is disconnected");

                    broadCast(name + " left the chatroom", this);
                    Server.clientList.remove(this);
                    socket.close();
                    break;
                }
            
                System.out.println(name + ": " + msg);
                broadCast(name + ": " + msg, this);
            }
        } catch (Exception e) {
            System.out.println(name + "connection error: " + e);
        }
    }

    // send message to all client except the sender
    void broadCast(String message, ClientHandler sender) {
        for (ClientHandler client : Server.clientList) {
            try {
                if (client != sender) {
                    client.dout.writeUTF(message);
                    client.dout.flush();
                }
            } catch (Exception e) {
                System.out.println("Broadcast error: " + e);
            }
        }
    }
}