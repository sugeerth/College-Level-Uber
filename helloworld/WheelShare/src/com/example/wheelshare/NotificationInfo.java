package com.example.wheelshare;

public class NotificationInfo {
	public String destination;
	public String source;
	public String userName;
	public char gender;
	public int price;
	//0 = request sent 
	//1 = request received 
	int requestType;
	//date
	int month;
	int day;
	int year;
	/*Everyone starts in state 0
	 * When a user finds another user in SEARCH, both user's state is changed to 1
	 * When the second user accepts first user, state=2
	 */
	public int state;
	//0 rider   1 for driver
	public int userType;
	
	NotificationInfo(String user, String source, String dest,char gender, int type, int requestType, int m, int d, int y, int price){
		userName = user;
		this.source = source;
		destination = dest;
		this.gender = gender;
	    userType = type;
	    this.requestType = requestType;
		month = m;
		day = d;
		year = y;
		this.price = price;
	}
	
	public String printDate(){
		return month + "/" + day + "/" + year;
	}
}
