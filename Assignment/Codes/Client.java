import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 3000);
            System.out.println("Successfully connected to the server");

            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter your name: ");
            String name = sc.nextLine();
            dout.writeUTF(name);

            // thread for receiving message using lambda function
            Thread receiveMessage = new Thread(() ->{
                try {
                    while (true) {
                        String msg = din.readUTF();
                        System.out.println(msg);
                    }
                } catch (Exception e) {
                    System.out.println("Receive message error: " + e);
                }
            });

            // thread for sending message using lambda function
            Thread sendMessage = new Thread(() ->{
                try {
                    while (true) {
                        String msg = sc.nextLine();
                        
                        dout.writeUTF(msg);
                        dout.flush();

                        if(msg.equalsIgnoreCase("exit")){
                            s.close();
                           sc.close();
                           break; 
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Sending message error: " + e);
                }
            });

            receiveMessage.start();
            sendMessage.start();
        } catch (Exception e) {
            System.out.println("Client method error: " + e);
        }
    }
}
