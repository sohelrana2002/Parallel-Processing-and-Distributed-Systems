package RMI;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;


public class RMIServer extends UnicastRemoteObject implements CalcService {
    public RMIServer() throws RemoteException {
    };

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

    public static void main(String [] args) {
        try {
            RMIServer rmiserver = new RMIServer();
            Registry registry = LocateRegistry.createRegistry(7000);

            registry.bind("CalcService", rmiserver);

            System.out.println("Server is running & waiting for remote call.");
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
