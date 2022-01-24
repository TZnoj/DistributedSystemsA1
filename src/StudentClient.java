import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class StudentClient {
	public static void main(String[] args) throws Exception{
		
		Scanner in = new Scanner(System.in);
		String choice;
		boolean programRunning = true;
		
		//Finding the server and binding the function
		Registry registry = LocateRegistry.getRegistry(2964);		
		StudentInterface obj = (StudentInterface) registry.lookup("Booking");
		
		obj.displayRooms();
		String bookingID = obj.bookRoom("0001", "19-10-2021", "08:00 to 09:00", "John");
		System.out.println(obj.getAvailableTimeSlot());
		obj.displayRooms();
		obj.cancelBooking(bookingID);
		System.out.println(obj.getAvailableTimeSlot());
	}
}
