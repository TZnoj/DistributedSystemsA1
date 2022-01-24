import java.util.concurrent.*;

public class RoomObject {

	String roomNumber;
	ConcurrentHashMap<String,ConcurrentHashMap<String,String>> Dates = new ConcurrentHashMap<String,ConcurrentHashMap<String,String>>();
	
	/*
	 * populating
	 * innerMap.put("InnerKey", "InnerValue");
	 *	outerMap.put("OuterKey", innerMap);
	 *
	 *retrieving
	 *  String value = ((HashMap<String, String>)outerMap.get("OuterKey")).get("InnerKey").toString();
	 *  System.out.println("Retrieved value is : " + value);
	 *
	 */
	
	public RoomObject(String roomNumber){
		this.roomNumber = roomNumber;
		//Times.put("8:00 to 9:00", "null");
		//ConcurrentHashMap does not allow null values so I just have strings with the text "null"
		//Times.put("9:00 to 10:00", "null");
		//Times.put("10:00 to 11:00", "null");
		Dates.put("19-10-2021", new ConcurrentHashMap<String,String>());
		Dates.put("20-10-2021", new ConcurrentHashMap<String,String>());
		Dates.get("20-10-2021").put("08:00 to 09:00", "null");
		Dates.get("19-10-2021").put("08:00 to 09:00", "null");

	}
	
}
