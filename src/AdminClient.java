import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class AdminClient {
	public static void main(String[] args) throws Exception{
		
		Scanner in = new Scanner(System.in);
		String choice;
		boolean programRunning = true;
		
		//Finding the server and binding the function
		Registry registry = LocateRegistry.getRegistry(2964);		
		AdminInterface obj = (AdminInterface) registry.lookup("Booking");
		
	
	}
}
