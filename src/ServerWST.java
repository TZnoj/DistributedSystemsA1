import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerWST {
		public static void main(String[] args) throws Exception{
			
			ServerClass objWST = new ServerClass();
			Registry registry = LocateRegistry.createRegistry(2966);
			registry.bind("Booking", objWST);
			System.out.println("Server is started");
		}

}
