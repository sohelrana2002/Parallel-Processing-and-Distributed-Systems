import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;


public class RMIClient {
    public static void main(String [] args) {
        try {
            Scanner sc = new Scanner(System.in);

            Registry registry = LocateRegistry.getRegistry("localhost", 5099);

            RemoteInterface arithmeticService = (RemoteInterface) registry.lookup("ArithmeticService");

            System.out.print("Enter two number: ");
            int a = sc.nextInt();
            int b = sc.nextInt();
            
            int sum = arithmeticService.add(a, b);
            
            int mul = arithmeticService.mul(a, b);

            System.out.println("Sum: " + sum);
            System.out.println("Multiplication: " + mul);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}

