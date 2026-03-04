package multiple_client_msg_passing;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 5000);
            System.out.println("Successfully connected to Server!");

            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String msg = "", reply = "";

            while (!msg.equals("exit")) {

                System.out.print("Client: ");
                msg = br.readLine();

                dout.writeUTF(msg);
                dout.flush();

                if (msg.equals("exit"))
                    break;

                reply = din.readUTF();
                System.out.println("Server says: " + reply);
            }

            din.close();
            dout.close();
            s.close();

        } catch (Exception e) {
            System.out.println("Client error: " + e);
        }
    }
}