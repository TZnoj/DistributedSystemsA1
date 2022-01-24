import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.*;

public class ServerClass extends UnicastRemoteObject implements AdminInterface, StudentInterface, CombinedInterface{
	
	ConcurrentHashMap<String,RoomObject> roomRecords = new ConcurrentHashMap<String,RoomObject>();
	
	public ServerClass() throws Exception{
		super();
		roomRecords.put("RR0001", new RoomObject("0001"));
	}

	
	//Admin Functions create and remove book
	public void createRoom(String roomNumber, String date, String[] listOfTimeSlots) {
		//Checks to see if the all the keys exists, and depending on where it ends up being null it will create either just
		// timeslots, days or even rooms
		String roomId = "RR".concat(roomNumber);
		if(roomRecords.containsKey(roomId)) {
			if(roomRecords.get(roomId).Dates.containsKey(date)) {
				for (String timeSlot : listOfTimeSlots) {
					if(roomRecords.get(roomId).Dates.get(date).contains(timeSlot) == false){
						
						roomRecords.get(roomId).Dates.get(date).put(timeSlot, "null");
					}
				}
			} else { 

				roomRecords.get(roomId).Dates.put(date, new ConcurrentHashMap<String,String>());
				for (String timeSlot : listOfTimeSlots) {
					if(roomRecords.get(roomId).Dates.get(date).containsKey(timeSlot) == false){
						roomRecords.get(roomId).Dates.get(date).put(timeSlot, "null");
					}
				}
				
			}
		} else {
			roomRecords.put(roomId, new RoomObject(roomNumber));
			roomRecords.get(roomId).Dates.put(date, new ConcurrentHashMap<String,String>());
			for (String timeSlot : listOfTimeSlots) {
				if(roomRecords.get(roomId).Dates.get(date).containsKey(timeSlot) == false){
					roomRecords.get(roomId).Dates.get(date).put(timeSlot, "null");
				}
			}
		}
	}
	
	public void deleteRoom(String roomNumber, String date, String[] listOfTimeSlots) {
		//Checks to see if there even is something to delete
		String roomId = "RR".concat(roomNumber);
		if(roomRecords.containsKey(roomId)) {
			if(roomRecords.get(roomId).Dates.containsKey(date)) {
				for (String timeSlot : listOfTimeSlots) {
					if(roomRecords.get(roomId).Dates.get(date).containsKey(timeSlot) == true){
						roomRecords.get(roomId).Dates.get(date).remove(timeSlot);
					}
				}
			}
		}
	}
	
	public String displayRooms() {
		//Simple toString function
		String rooms = "";
		for (String roomId : roomRecords.keySet()) {
			rooms = rooms + roomId + "\n";
			for (String date : roomRecords.get(roomId).Dates.keySet()) {
				rooms = rooms + date + "\n";
				for (String timeSlot : roomRecords.get(roomId).Dates.get(date).keySet()) {
					rooms = rooms + timeSlot  + " " + roomRecords.get(roomId).Dates.get(date).get(timeSlot) + "\n";
				}
			}
		}
		return rooms;
	}
	
	//Student Functions
	public String bookRoom(String  roomNumber, String date, String timeslot, String studentId) {
		//If the room can't be booked then returns an empty string
		String bookingID = "";
		String roomId = "RR".concat(roomNumber);
		//Checks to see how many rooms the student has booked
		if(roomRecords.containsKey(roomId) && this.checkBookedRooms(studentId) < 3) {
			if(roomRecords.get(roomId).Dates.containsKey(date)) {
				if ((roomRecords.get(roomId).Dates.get(date).containsKey(timeslot))) {
					//Checks if the room has no one booking it
					if((roomRecords.get(roomId).Dates.get(date).get(timeslot).equals("null"))){
						roomRecords.get(roomId).Dates.get(date).put(timeslot, studentId);
						bookingID = roomId.concat(date).concat(timeslot).concat(studentId);
					}
				}
			}
		}
		return bookingID;
					
	}
	
	public int checkBookedRooms(String studentId) {
		//Iterates through the hashmap and checks if rooms are booked
		int roomsBooked = 0;
				for (String roomId : roomRecords.keySet()) {
					for (String date : roomRecords.get(roomId).Dates.keySet()) {
						for (String timeSlot : roomRecords.get(roomId).Dates.get(date).keySet()) {
							if(roomRecords.get(roomId).Dates.get(date).get(timeSlot).equals(studentId)){
								roomsBooked = roomsBooked + 1;
							}
						}
					}
				}
				return roomsBooked;
	}
	
	public int getAvailableTimeSlot() {
		//Iterates through the hashmap and checks if rooms are not booked
		int availableSlots = 0;
		for (String roomId : roomRecords.keySet()) {
			for (String date : roomRecords.get(roomId).Dates.keySet()) {
				for (String timeSlot : roomRecords.get(roomId).Dates.get(date).keySet()) {
					if(roomRecords.get(roomId).Dates.get(date).get(timeSlot).equals("null")) {
						availableSlots = availableSlots + 1;
					}
				}
			}
		}
		return availableSlots;
		
	}
	
	public void cancelBooking(String bookingID) {
		//Uses the bookingID string to see if a room can be unbooked, the error checking is on the client side
		String roomID = bookingID.substring(0,6);
		String date = bookingID.substring(6,16);
		String timeslot = bookingID.substring(16,30);
		String studentID = bookingID.substring(30);
		if(roomRecords.containsKey(roomID)) {
			if(roomRecords.get(roomID).Dates.containsKey(date)) {
				if(roomRecords.get(roomID).Dates.get(date).containsKey(timeslot)) {
					if(roomRecords.get(roomID).Dates.get(date).get(timeslot).equals(studentID)) {
						roomRecords.get(roomID).Dates.get(date).put(timeslot, "null");
					}
				}
			}
		}
	}

}
