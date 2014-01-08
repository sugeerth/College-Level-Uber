package com.example.wheelshare;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class activity_home extends activity_base {

	Button postBttn, searchBttn, settingBttn;
	private static UserInfo userInfo;
	private AdInfo adInfo;
	private static ListView notificationList;
	private static Activity homeActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		// get user info from login screen by parcel
		Bundle b = getIntent().getExtras();
		userInfo = b.getParcelable("userInfo");
		homeActivity = this;
		init();
		searchBttnListener();

		new GetRequestFormTask(userInfo, homeActivity, notificationList)
				.execute(userInfo.getUserName());

		notificationList.setOnItemClickListener(clickLister);

	}

	// Menu button listener
	public void searchBttnListener() {

		searchBttn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(activity_home.this,
						activity_search.class);
				intent.putExtra("userInfo", userInfo);
				startActivity(intent);
			}
		});
	}

	public void setRequestResults(List<AdInfo> results) {
		notificationList = (ListView) homeActivity
				.findViewById(R.id.notificationList);
		// create adapter
		RequestListAdapter requestListAdapter = new RequestListAdapter(
				homeActivity, results);
		// attach adapter to list view
		notificationList.setOnItemClickListener(clickLister);
		notificationList.setAdapter(requestListAdapter);

	}

	// click listener
	private AdapterView.OnItemClickListener clickLister = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> adapterView, View view, int pos,
				long l) {
			try {
				// get AdInfo from position in list and pass to result_detail
				// activity
				Intent intent = new Intent(homeActivity, activity_detail.class);
				adInfo = userInfo.getAdInfoList().get(pos);
				Log.i("status", adInfo.getStatus());

				intent.putExtra("adInfo", adInfo);

				startActivity(intent);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	private void init() {
		searchBttn = (Button) findViewById(R.id.searchMenu);
		notificationList = (ListView) homeActivity
				.findViewById(R.id.notificationList);

	}

	public static UserInfo getUserInfo() {
		return userInfo;
	}

}
