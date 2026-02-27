import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(5000);
            System.out.println("Server is running!");

            Socket s = ss.accept();
            System.out.println("Client connected successfully!");

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String clientMsg = "", serverMsg = "";

            while (!clientMsg.equalsIgnoreCase("esc")) {
                // read message from client 
                clientMsg = dis.readUTF();
                System.out.println("Client says: " + clientMsg);

                // send reply from the server 
                System.out.println("Server says: ");
                serverMsg = br.readLine();
                dos.writeUTF(serverMsg);
                dos.flush();
            }

            ss.close();
            s.close();
            br.close();
            dos.close();
            dis.close();
        } catch (Exception e) {
           System.err.println(e);
        }
    }
}