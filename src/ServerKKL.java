import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerKKL {
		public static void main(String[] args) throws Exception{
			
			ServerClass objKKL = new ServerClass();
			Registry registry = LocateRegistry.createRegistry(2965);
			registry.bind("Booking", objKKL);
			System.out.println("Server is started");
		}

}
