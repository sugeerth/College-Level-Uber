package com.example.wheelshare;

public class SearchInfo {

	public char gender;
	public String destination;
	public String source;
	//date
	int month;
	int day;
	int year;
	int fare;
	SearchInfo(String dest, String source, char g, int m, int d, int y,int fare){
		destination = dest;
		this.source = source;
		gender = g;
		month = m;
		day = d;
		year = y;
		this.fare = fare;
	}
	
	public String printDate(){
		return month + "/" + day + "/" + year;
	}
}
