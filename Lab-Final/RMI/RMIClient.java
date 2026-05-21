package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class RMIClient {
    public static void main(String [] args) {
        try {

            Registry registry = LocateRegistry.getRegistry("localhost", 7000);

            CalcService calcService = (CalcService) registry.lookup("CalcService");
            
            int sum = calcService.add(10, 25);

            System.out.println("Sum: " + sum);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}

