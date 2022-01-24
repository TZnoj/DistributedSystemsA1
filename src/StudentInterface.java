import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StudentInterface extends Remote{
	
	public String bookRoom(String  roomNumber, String date, String timeslot, String studentId) throws RemoteException;
	
	public String displayRooms() throws RemoteException;
	
	public int getAvailableTimeSlot() throws RemoteException;
	
	public void cancelBooking(String bookingID) throws RemoteException;


}
