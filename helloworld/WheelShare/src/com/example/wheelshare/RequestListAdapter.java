package com.example.wheelshare;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RequestListAdapter extends BaseAdapter {

	LayoutInflater inflater;
	List<AdInfo> items;
	Activity context;

	public RequestListAdapter(Activity context, List<AdInfo> items) {
		super();
		this.context = context;
		this.items = items;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		// locate current item
		AdInfo info = items.get(position);
		View vi = convertView;

		if (convertView == null)
			vi = inflater.inflate(R.layout.request_list_row, null);

		ImageView profilePic = (ImageView) vi
				.findViewById(R.id.profile_picture);
		ImageView alert = (ImageView) vi.findViewById(R.id.alert_icon);
		TextView locationAdInfo = (TextView) vi
				.findViewById(R.id.locationRequestInfo);
		TextView userTypeAndFareAdInfo = (TextView) vi
				.findViewById(R.id.userTypeAndFareRequestInfo);
		TextView dateAdInfo = (TextView) vi.findViewById(R.id.dateRequestInfo);
		TextView posterAdInfo = (TextView) vi
				.findViewById(R.id.sentToRequestInfo);

		locationAdInfo.setText(info.getSourceLocation() + " -> "
				+ info.getDestinationLocation());
		// check gender
		if (isMale(info.getPosterInfo().getGender()))
			profilePic.setImageResource(R.drawable.default_male);
		else
			profilePic.setImageResource(R.drawable.default_female);

		// check status and state
		Log.i("STATE", Integer.toString(info.getState()));

		if (info.getStatus().equals("poster")) {

			Log.i("REQUEST LIST ADAPTER poster username:", info.getUserType());

			posterAdInfo.setText("Request send to: "
					+ info.getPosterInfo().getFirstName() + " "
					+ info.getPosterInfo().getLastName().charAt(0) + ".");

			switch (info.getState()) {
			case 0:
				alert.setImageResource(R.drawable.alert_icon_red);
				userTypeAndFareAdInfo.setText(info.getPosterInfo()
						.getFirstName()
						+ " "
						+ info.getPosterInfo().getLastName().charAt(0)
						+ "."
						+ " has denied your invitation.");
				break;
			case 1:
				// accept or reject requester
				alert.setImageResource(R.drawable.alert_icon_yellow);
				userTypeAndFareAdInfo.setText("New request!");

				break;
			case 2:
				// wait for payment pending
				alert.setImageResource(R.drawable.alert_icon_green);
				userTypeAndFareAdInfo.setText("Awaiting payment");
				break;

			case 3:
				// server verifies requesters credit card and waits for
				// start date
				alert.setImageResource(R.drawable.alert_icon_green);
				userTypeAndFareAdInfo
						.setText("Payment received. Awaiting departure date.");

				break;
			case 4:
				// notify that requester has checked in and pressed begin
				// trip
				alert.setImageResource(R.drawable.alert_icon_green);
				userTypeAndFareAdInfo.setText(info.getPosterInfo()
						.getFirstName()
						+ " "
						+ info.getPosterInfo().getLastName().charAt(0)
						+ " has checked in!");

				break;

			case 5:
				// subtract server escrow account, transfer money to driver
				// notified driver has been paid
				alert.setImageResource(R.drawable.alert_icon_green);
				userTypeAndFareAdInfo.setText("You have been paid $"
						+ info.getFare() + "! Please rate this trip");

				break;
			}

		} else if (info.getStatus().equals("requester")) {

			Log.i("REQUEST LIST ADAPTER requester username:",
					info.getUserType());

			posterAdInfo.setText("Request from: "
					+ info.getPosterInfo().getFirstName() + " "
					+ info.getPosterInfo().getLastName().charAt(0) + ".");

			switch (info.getState()) {
			case 0:
				// poster has rejected requester
				alert.setImageResource(R.drawable.alert_icon_red);
				userTypeAndFareAdInfo.setText(info.getPosterInfo()
						.getFirstName()
						+ " "
						+ info.getPosterInfo().getLastName().charAt(0)
						+ "."
						+ " has denied your invitation.");
				break;
			case 1:
				// waiting for reply from poster
				alert.setImageResource(R.drawable.alert_icon_yellow);
				userTypeAndFareAdInfo.setText("Request send to: "
						+ info.getPosterInfo().getFirstName() + " "
						+ info.getPosterInfo().getLastName().charAt(0) + ".");
				break;
			case 2:
				// got reply and ready to pay poster
				alert.setImageResource(R.drawable.alert_icon_green);
				userTypeAndFareAdInfo.setText(info.getPosterInfo()
						.getFirstName()
						+ " "
						+ info.getPosterInfo().getLastName().charAt(0)
						+ "."
						+ " has accepted your request! Payment required");
				break;
			case 3:
				// start button shows on the date of departure
				alert.setImageResource(R.drawable.alert_icon_green);
				userTypeAndFareAdInfo.setText("Awaiting departure date with "
						+ info.getPosterInfo().getFirstName() + " "
						+ info.getPosterInfo().getLastName().charAt(0) + ".");

				break;
			case 4:
				// the finish trip button shows as soon as start button is
				// pressed
				alert.setImageResource(R.drawable.alert_icon_green);
				userTypeAndFareAdInfo
						.setText("You have arrived! Press finish to end trip "
								+ info.getPosterInfo().getFirstName() + " "
								+ info.getPosterInfo().getLastName().charAt(0)
								+ ".");

				break;
			case 5:
				// the finish trip button shows as soon as start button is
				// pressed
				alert.setImageResource(R.drawable.alert_icon_green);
				userTypeAndFareAdInfo.setText("Please rate your trip with "
						+ info.getPosterInfo().getFirstName() + " "
						+ info.getPosterInfo().getLastName().charAt(0) + ".");

				break;
			}
		}

		dateAdInfo.setText("Date: " + info.getDate());
		return vi;
	}

	boolean isMale(String gender) {
		if (gender.equals("m"))
			return true;
		else
			return false;
	}
}