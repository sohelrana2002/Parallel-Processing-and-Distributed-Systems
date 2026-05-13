import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 5000);

            if (s.isConnected()) {
                System.out.println("Client try to connect.");
            } else {
                System.out.println("Cleint try to connect. Please wait");
            }

            s.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
