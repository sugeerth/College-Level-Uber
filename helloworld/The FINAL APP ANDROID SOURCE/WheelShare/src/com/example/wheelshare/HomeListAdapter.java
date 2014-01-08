package com.example.wheelshare;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeListAdapter extends BaseAdapter {

	LayoutInflater inflater;
	List<AdInfo> items;
	Activity context;

	public HomeListAdapter(Activity context, List<AdInfo> items) {
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
			vi = inflater.inflate(R.layout.ad_list_row, null);

		/* Setting views by their ID */
		ImageView profilePic = (ImageView) vi
				.findViewById(R.id.profile_picture);
		TextView locationAdInfo = (TextView) vi
				.findViewById(R.id.locationAdInfo);
		TextView userTypeAndFareAdInfo = (TextView) vi
				.findViewById(R.id.userTypeAndFareAdInfo);
		TextView dateAdInfo = (TextView) vi.findViewById(R.id.dateAdInfo);
		TextView posterAdInfo = (TextView) vi.findViewById(R.id.posterAdInfo);

		/* Set source and Location */
		locationAdInfo.setText(info.getSourceLocation() + " -> "
				+ info.getDestinationLocation());
		/* Check gender */
		if (isMale(info.getPosterInfo().getGender()))
			profilePic.setImageResource(R.drawable.default_male);
		else
			profilePic.setImageResource(R.drawable.default_female);
		/*
		 * Check poster type 0=rider 1=driver
		 */
		if (info.getUserType().equals("rider"))
			userTypeAndFareAdInfo.setText("Passenger requested: " + "$"
					+ info.getFare());
		else
			userTypeAndFareAdInfo.setText("Driver requested: " + "$"
					+ info.getFare());
		/* set deparature date and name of poster */
		dateAdInfo.setText("Date: " + info.getDate());
		posterAdInfo.setText("Sent to: " + info.getPosterInfo().getFirstName()
				+ " " + info.getPosterInfo().getLastName().charAt(0) + ".");

		// request received
		if (info.getUserType().equals("driver")) {
			if (isMale(info.getPosterInfo().getGender()))
				profilePic.setImageResource(R.drawable.default_male);
			else
				profilePic.setImageResource(R.drawable.default_female);

			locationAdInfo.setText("NEW REQUEST");
			userTypeAndFareAdInfo.setText("");
			posterAdInfo.setText("From: " + info.getPosterInfo().getFirstName()
					+ " " + info.getPosterInfo().getLastName().charAt(0) + ".");
		}

		return vi;
	}

	boolean isMale(String gender) {
		if (gender.equals("m"))
			return true;
		else
			return false;
	}
}