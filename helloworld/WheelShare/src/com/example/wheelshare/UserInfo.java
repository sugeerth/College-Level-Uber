package com.example.wheelshare;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class UserInfo implements Parcelable {
	/* member variables, used to store current user's information */
	private String userName;
	private String firstName;
	private String lastName;
	private String gender;
	private String creditCardNum;
	private int totalRides;
	private int successfulRides;
	private List<AdInfo> adInfo;

	// constructor
	public UserInfo(String userName, String firstName, String lastName,
			String gender, List<AdInfo> adInfo, int totalRides,
			int successfulRides, String creditCardNum) {
		setUserName(userName);
		setFirstName(firstName);
		setLastName(lastName);
		setGender(gender);
		setCreditCardNum(creditCardNum);

		this.totalRides = totalRides;
		this.successfulRides = successfulRides;
		this.adInfo = adInfo;
	}
	
	

	// constructor for poster info, AdInfo ArrayList = null
	public UserInfo(String userName, String firstName, String lastName,
			String gender, int totalRides, int successfulRides,
			String creditCardNum) {
		setUserName(userName);
		setFirstName(firstName);
		setLastName(lastName);
		setGender(gender);
		setCreditCardNum(creditCardNum);
		this.totalRides = totalRides;
		this.successfulRides = successfulRides;
		this.adInfo = null;

	}

	// constructor for parcel read
	public UserInfo(Parcel in) {
		this.userName = in.readString();
		this.firstName = in.readString();
		this.lastName = in.readString();
		this.gender = in.readString();	
		this.totalRides = in.readInt();
		this.successfulRides = in.readInt();
		this.creditCardNum = in.readString();
		if (adInfo != null)
			in.readTypedList(adInfo, AdInfo.CREATOR);

	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(userName);
		dest.writeString(firstName);
		dest.writeString(lastName);
		dest.writeString(gender);
		dest.writeInt(totalRides);
		dest.writeInt(successfulRides);
		dest.writeString(creditCardNum);
		dest.writeTypedList(adInfo);

	}

	public static final Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() {

		@Override
		public UserInfo createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new UserInfo(source);
		}

		@Override
		public UserInfo[] newArray(int size) {
			// TODO Auto-generated method stub
			return new UserInfo[size];
		}

	};

	// mutators & accessors
	public void setUserName(String userName) {
		/* Cannot start with numbers, must be less than or equal 15 characters */
		if (userName.length() > 15 || Character.isDigit(userName.charAt(0)))
			throw new IllegalStateException("Invalid username.");
		else
			this.userName = userName;
	}

	public void setFirstName(String firstName) {
		/* Cannot contain numbers, must be less than or equal 20 characters */
		if (firstName.length() > 20 || Pattern.matches("[0-9]+", firstName))
			throw new IllegalStateException("Invalid username.");
		else
			this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		/* Cannot contain numbers, must be less than or equal 20 characters */
		if (lastName.length() > 20 || Pattern.matches("[0-9]+", lastName))
			throw new IllegalStateException("Invalid username.");
		else
			this.lastName = lastName;
	}

	public void setGender(String gender) {
		if (gender.equals("m") || gender.equals("f"))
			this.gender = gender;
		else
			throw new IllegalStateException(
					"Invalid gender, must be 'm' or 'f'.");
	}

	public void setCreditCardNum(String creditCardNum) {
		this.creditCardNum = creditCardNum;
	}

	public void setAdInfoList(List<AdInfo> adInfo) {
		this.adInfo = adInfo;
	}

	public void setTotalRides(int total) {
		totalRides = total;
	}

	public void setSuccessfulRides(int total) {
		successfulRides = total;
	}
	
	public void putRequestAd(AdInfo ad){
		if(adInfo==null){
			adInfo = new ArrayList<AdInfo>();
			adInfo.add(ad);
		}
		else
			adInfo.add(ad);
	}

	public int getTotalRides() {
		return totalRides;
	}

	public int getSuccessfulRides() {
		return successfulRides;
	}

	public String getUserName() {
		return userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getGender() {
		return gender;
	}

	public String getCreditCardNum() {
		return creditCardNum;
	}

	public List<AdInfo> getAdInfoList() {
		return adInfo;
	}

}
