# Distributed Systems Assignment
**Assignment Description**  
The task for this assignment was to create a room booking service for a fictional university with 3 different campuses. Students and Admins needed to be able to use a client to book rooms, create rooms and check availabilities concurrently and without any data erasure. All of this had to be done using the Java RMI api for the networking protocol, though it was not needed to be accessed from different machines.  
**My Implementation**  
My solution to the concurrency issue was to implement concurrent hashmaps from the Java library rather than implement my own synchronization tools. By using this data structure I avoided the need to reinvent the wheel and reduced the amount of code needed to accomplish the same function.
