import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AdminInterface extends Remote{
	
	public void createRoom(String roomNumber, String date, String[] listOfTimeSlots) throws RemoteException;
	
	public void deleteRoom(String roomNumber, String date, String[] listOfTimeSlots) throws RemoteException;
	
	public String displayRooms() throws RemoteException;
	
}
