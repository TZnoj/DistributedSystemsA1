import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerDVL {
		public static void main(String[] args) throws Exception{
			
			ServerClass objDVL = new ServerClass();
			Registry registry = LocateRegistry.createRegistry(2964);
			registry.bind("Booking", objDVL);
			System.out.println("Server is started");
		}

}
