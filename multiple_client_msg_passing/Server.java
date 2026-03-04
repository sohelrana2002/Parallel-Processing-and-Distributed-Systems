package multiple_client_msg_passing;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(5000);
            System.out.println("Server is running!");

            int count = 0;

            while (true) {
                Socket s = ss.accept();
                System.out.println("New client is connected!");
                count++;

                ClientHandler ch = new ClientHandler(s, count);
                new Thread(ch).start();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}

class ClientHandler implements Runnable {
    Socket s;
    Scanner input;
    DataInputStream din;
    DataOutputStream dout;
    String str1 = "", str2 = "";
    int count;

    public ClientHandler(Socket s, int count) {
        try {
            this.s = s;
            this.din = new DataInputStream(s.getInputStream());
            this.dout = new DataOutputStream(s.getOutputStream());
            this.input = new Scanner(System.in);
            this.count = count;
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void run() {
        try {
            while (!str1.equals("exit")) {
                str1 = din.readUTF();
                System.out.println("Client " + count + " says: " + str1);

                System.out.print("Server: ");
                str2 = input.nextLine();
                dout.writeUTF(str2);
                dout.flush();
            }
            
            din.close();
            dout.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}