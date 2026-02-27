import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 5000);
            System.out.println("Connected to the server!");

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String clientMsg = "", serverMsg = "";

            while (!clientMsg.equalsIgnoreCase("esc")) {
                // send message to the server 
                System.out.println("Client says: ");
                clientMsg = br.readLine();
                dos.writeUTF(clientMsg);
                dos.flush();

                // read message from the server 
                serverMsg = dis.readUTF();
                System.out.println("Server says: " + serverMsg);
            }

            dos.close();
            dis.close();
            br.close();
            s.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
