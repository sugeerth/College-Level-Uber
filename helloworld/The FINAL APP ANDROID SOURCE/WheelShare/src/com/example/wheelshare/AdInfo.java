package com.example.wheelshare;

import android.os.Parcel;
import android.os.Parcelable;

/* This class contains information about the posted driver/rider wanted advertisement
 * It will be used to display various information on the search result and
 * home page.
 */
public class AdInfo implements Parcelable {

	private UserInfo posterInfo;

	private String sourceLocation;
	private String destinationLocation;
	private String userType;
	private String seats;
	private String description;
	private String status;
	/*
	 * Everyone starts in state 0 When a user finds another user in SEARCH, both
	 * user's state is changed to 1 When the second user accepts first user,
	 * state=2
	 */
	private int state;
	private int fare;
	private long postID;

	/* ****date, need to change this**** */
	String date, day, month, year;

	/* Constructor */
	public AdInfo(UserInfo posterInfo, String sourceLocation,
			String destinationLocation, String userType, int fare, int state,
			String date, String seats, String description, long postID,
			String status) {

		this.posterInfo = posterInfo;
		setSourceLocation(sourceLocation);
		setDestinationLocation(destinationLocation);
		setUserType(userType);
		setFare(fare);
		setState(state);
		setDate(date);
		// split date to return individual month/day/year
		String[] splitDate = date.split("/");
		month = splitDate[0];
		day = splitDate[1];
		year = splitDate[2];
		this.seats = seats;
		this.description = description;
		this.status = status;
		this.postID = postID;
		

	}

	/* Parcelable Constructor */
	public AdInfo(Parcel in) {
		this.sourceLocation = in.readString();
		this.destinationLocation = in.readString();
		this.userType = in.readString();
		this.date = in.readString();
		this.fare = in.readInt();
		this.state = in.readInt();
		this.postID = in.readLong();
		this.seats = in.readString();
		this.day = in.readString();
		this.month = in.readString();
		this.year = in.readString();
		this.description = in.readString();
		this.status = in.readString();
		this.posterInfo = in.readParcelable(UserInfo.class.getClassLoader());
		

	}

	// Parcelable override method
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(sourceLocation);
		dest.writeString(destinationLocation);
		dest.writeString(userType);
		dest.writeString(date);
		dest.writeInt(fare);
		dest.writeInt(state);
		dest.writeLong(postID);
		dest.writeString(seats);
		dest.writeString(day);
		dest.writeString(month);
		dest.writeString(year);
		dest.writeString(description);
		dest.writeString(status);
		dest.writeParcelable(posterInfo, 0);
		
	}

	// the CREATOR
	public static final Parcelable.Creator<AdInfo> CREATOR = new Parcelable.Creator<AdInfo>() {

		@Override
		public AdInfo createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new AdInfo(source);
		}

		@Override
		public AdInfo[] newArray(int size) {
			// TODO Auto-generated method stub
			return new AdInfo[size];
		}

	};

	// Mutators

	public void setSourceLocation(String sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	public void setDestinationLocation(String destinationLocation) {
		this.destinationLocation = destinationLocation;
	}

	public void setState(int state) {
		if (state > 5 || state < 0)
			throw new IllegalStateException("Invalid State");
		else
			this.state = state;
	}

	public void setUserType(String userType) {
		if (userType.equals("driver") || userType.equals("rider"))
			this.userType = userType;
		else
			throw new IllegalStateException(
					"Invalid usertype, must be driver or rider");
	}

	public void setFare(int fare) {
		if (fare < 0)
			throw new IllegalStateException("Fare cannot be negative.");
		else
			this.fare = fare;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setSeat(String seats) {
		this.seats = seats;
	}

	public String getSeats() {
		return this.seats;
	}

	public void setDescription(String Descrip) {
		this.description = Descrip;
	}

	public void setPostID(long id) {
		postID = id;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public String getDescription() {
		return this.description;
	}

	public String getSourceLocation() {
		return sourceLocation;
	}

	public String getDestinationLocation() {
		return destinationLocation;
	}

	public String getUserType() {
		return userType;
	}

	public int getFare() {
		return fare;
	}

	public int getState() {
		return state;
	}

	public String getDate() {
		return date;
	}

	public String getDay() {
		return day;
	}

	public String getMonth() {
		return month;
	}

	public String getYear() {
		return year;
	}

	public UserInfo getPosterInfo() {
		return posterInfo;
	}

	public long getPostID() {
		return postID;
	}

}
