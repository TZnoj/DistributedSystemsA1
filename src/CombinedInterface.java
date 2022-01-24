import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CombinedInterface extends Remote{
	
	public void createRoom(String roomNumber, String date, String[] listOfTimeSlots) throws RemoteException;
	
	public void deleteRoom(String roomNumber, String date, String[] listOfTimeSlots) throws RemoteException;	
	
	public String bookRoom(String  roomNumber, String date, String timeslot, String studentId) throws RemoteException;
	
	public int getAvailableTimeSlot() throws RemoteException;
	
	public void cancelBooking(String bookingID) throws RemoteException;
	
	public String displayRooms() throws RemoteException;

}
