import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;


public class RMIServer extends UnicastRemoteObject implements RemoteInterface {
    public RMIServer() throws RemoteException {
    };

    // Implement the remote method declared in the interface.
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

    public int mul(int a, int b) throws RemoteException {
        return a * b;
    }

    public static void main(String [] args) {
        try {
            RMIServer rmiserver = new RMIServer();
            Registry registry = LocateRegistry.createRegistry(5099);

            registry.bind("ArithmeticService", rmiserver);

            System.out.println("Server is waiting for remote call...");
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
