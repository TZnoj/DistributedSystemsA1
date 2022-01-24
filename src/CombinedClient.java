import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.io.*;
import java.time.*;

public class CombinedClient {
	public static void main(String[] args) throws Exception{
		FileWriter out = null;
		Scanner in = new Scanner(System.in);
		String ID;
		String choice;
		boolean programRunning = false;
		//Finding the servers and binding the functions
		Registry registryDVL = LocateRegistry.getRegistry(2964);
		Registry registryKKL = LocateRegistry.getRegistry(2965);	
		Registry registryWST = LocateRegistry.getRegistry(2966);	
		CombinedInterface objDVL = (CombinedInterface) registryDVL.lookup("Booking");
		CombinedInterface objKKL = (CombinedInterface) registryKKL.lookup("Booking");
		CombinedInterface objWST = (CombinedInterface) registryWST.lookup("Booking");
		
		System.out.println("Please enter your school ID");
		ID = in.nextLine();
		
		try {
			String filename = ID + ".txt";
			out = new FileWriter(filename);
		} catch (Exception e) {}
		
		
		//Checks to see if the user is an admin, student or other
		if(ID.charAt(3) == 'A' || ID.charAt(3) == 'S' ) {
			programRunning = true;
		} else { System.out.println("Incorrect ID");}
		
		while(programRunning == true) {
			//Admin functions, simple if tree with user input
			if(ID.charAt(3) == 'A') {
				System.out.println("Choose an option:\nA : create timeslot\nB : delete timeslot\nC : display\nD : exit");
				choice = in.nextLine();
				if(choice.equals("A")) {
					System.out.println("Enter the room number(XXXX) then the date (XX-XX-XXXX)");
					String number = in.nextLine();
					String date = in.nextLine();
					System.out.println("How many timeslots?");
					int timeslotAmount = in.nextInt();
					//changing it to index format
					String[] timeslots = new String[timeslotAmount];
					in.nextLine();
					for(int i = 0; i < timeslotAmount; i++) {
						System.out.println("Enter a  timeslot (XX:XX to XX:XX)");
						timeslots[i] = in.nextLine();
						in.nextLine();
						System.out.println();
					}
					if(ID.substring(0, 3).equals("DVL")) {
						objDVL.createRoom(number, date, timeslots);
					}
					if(ID.substring(0, 3).equals("KKL")) {
						objKKL.createRoom(number, date, timeslots);
					}
					if(ID.substring(0, 3).equals("WST")) {
						objWST.createRoom(number, date, timeslots);
					}
					LocalDateTime time = LocalDateTime.now();
					String message = "" + time+ "\n" + ID.substring(0, 3)  + " Admin " + ID + " created rooms " + number + " with day " + date + " with timeslots: ";
					for(int i = 0; i < timeslots.length; i++) {
						message = message + "\n" + timeslots[i];
					}
					out.write(message);
				}
				if(choice.equals("B")) {
					System.out.println("Enter the room number(XXXX) then the date (XX-XX-XXXX)");
					String number = in.nextLine();
					String date = in.nextLine();
					System.out.println("How many timeslots?");
					int timeslotAmount = in.nextInt();
					String[] timeslots = new String[timeslotAmount];
					in.nextLine();
					for(int i = 0; i < timeslotAmount; i++) {
						System.out.println("Enter a  timeslot (XX:XX to XX:XX)");
						String timeslot = in.nextLine();
						in.nextLine();
						timeslots[i] = timeslot;
					}
					if(ID.substring(0, 3).equals("DVL")) {
						objDVL.deleteRoom(number, date, timeslots);
					}
					if(ID.substring(0, 3).equals("KKL")) {
						objKKL.deleteRoom(number, date, timeslots);
					}
					if(ID.substring(0, 3).equals("WST")) {
						objWST.deleteRoom(number, date, timeslots);
					}
					LocalDateTime time = LocalDateTime.now();
					String message = "" + time + "\n" +ID.substring(0, 3)  + " Admin " + ID + " deleted rooms " + number + " with day " + date + " with timeslots: ";
					for(int i = 0; i < timeslots.length; i++) {
						message = message + "\n" + timeslots[i];
					}
					out.write(message);
				}
				if(choice.equals("C")) {
					if(ID.substring(0, 3).equals("DVL")) {
						System.out.println(objDVL.displayRooms());
					}
					if(ID.substring(0, 3).equals("KKL")) {
						System.out.println(objKKL.displayRooms());
					}
					if(ID.substring(0, 3).equals("WST")) {
						System.out.println(objWST.displayRooms());
					}
				}				
				if(choice.equals("D")) {
					programRunning = false;
				}
				
			}
			//Student functions, simple if tree with user input
			else { System.out.println("Choose an option:\nA : book room timeslot\nB : check available amount\nC : cancelbooking\nD : exit");
			choice = in.nextLine();
			if(choice.equals("A")) {
				in.nextLine();
				System.out.println("Enter the room number(XXXX) then the date (XX-XX-XXXX) then the time slot (XX:XX to XX:XX) then the campus abbreviation");
				String number = in.nextLine();
				String date = in.nextLine();
				String timeslot = in.nextLine();
				String campus = in.nextLine();
				String bookingID = "";
				if(campus.equals("DVL")) {
					bookingID = objDVL.bookRoom(number, date, timeslot, ID);
				}
				if(campus.equals("KKL")) {
					bookingID = objKKL.bookRoom(number, date, timeslot, ID);
				}
				if(campus.equals("WST")) {
					bookingID = objWST.bookRoom(number, date, timeslot, ID);
				}
				if(bookingID.equals("")) {
					System.out.println("Failed to book the room");
					LocalDateTime time = LocalDateTime.now();
					String message = "" + time+ "\nStudent " + ID + " failed to book room " + number + " with day " + date + " during timeslots: " + timeslot + "on campus " + campus;
					out.write(message);
					continue;
				}
				System.out.println(bookingID);
				LocalDateTime time = LocalDateTime.now();
				String message = "" + time+ "\nStudent " + ID + " booked room " + number + " with day " + date + " during timeslots: " + timeslot + "on campus " + campus + "with booking ID " + bookingID;
				out.write(message);
			}
			if(choice.equals("B")) {
				System.out.println("DVL: " + objDVL.getAvailableTimeSlot() + " KKL: " + objKKL.getAvailableTimeSlot() + " WST: " + objWST.getAvailableTimeSlot());
				String message = "\nStudent " + ID + " checked the available time slots";
			}	
			if(choice.equals("C")) {
				System.out.println("Enter the booking ID then the campus abbreviation");
				String bookingID = in.nextLine();
				String campus = in.nextLine();
				if(!ID.equals(bookingID.substring(30))) {
					System.out.println("Only the student who made the booking may cancel it.");
					String message = "\nStudent " + ID + "tried to cancel another student's booking";
					out.write(message);
					continue;
				}
				if(campus.equals("DVL")) {
					objDVL.cancelBooking(bookingID);
				}
				if(campus.equals("KKL")) {
					objKKL.cancelBooking(bookingID);
				}
				if(campus.equals("WST")) {
					objWST.cancelBooking(bookingID);
				}
				String message = "\nStudent " + ID + " cancelled booking: " + bookingID;
				out.write(message);
				
			}	
			if(choice.equals("D")) {
				programRunning = false;
			}
				
			}
		}
		
	out.close();
	}
}
