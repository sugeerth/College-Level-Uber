package com.example.wheelshare;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

public class GetRequestFormTask extends AsyncTask<String, String, String[]> {
	private static final String TAG = "GetRequestForm";
	Context context;
	private UserInfo userInfo;
	private Activity homeActivity;
	private ListView notificationList;

	public GetRequestFormTask(UserInfo userInfo, Activity home, ListView list) {
		this.userInfo = userInfo;
		this.homeActivity = home;
		notificationList = list;
	}

	@Override
	protected String[] doInBackground(String... params) {

		String userName = params[0];

		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet request;

			request = new HttpGet(
					"http://helloworldws.appspot.com/getrequest?userName="
							+ userName);
			HttpResponse response = client.execute(request);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String temp = rd.readLine();
			Log.i(TAG, temp);
			String[] results = temp.split("_");
			return results;

		} catch (Exception e) {
			Log.i("ERROR", "ERROR IN GET REQUEST FORM TASK");
		}
		return null;
	}

	protected void onPostExecute(String[] result) {
		if (result != null) {
			List<AdInfo> items = new ArrayList<AdInfo>();
			int fare, totalRides, successfulRides, state;
			long postID;
			String destination, source, date, userName, firstName, lastName, gender, seats, description, userType, status;

			try {
				for (int i = 15; i < result.length + 15; i = i + 16) {
					Log.i("State in get request", result[i - 15]);
					fare = Integer.parseInt(result[i - 8]);
					state = Integer.parseInt(result[i - 15]);
					status = result[i - 13];
					postID = Long.parseLong(result[i - 14]);
					totalRides = Integer.parseInt(result[i - 12]);
					successfulRides = Integer.parseInt(result[i - 11]);

					Log.i("TOTAL RIDES IN GETREQ", "" + totalRides);
					Log.i("SUC RIDE IN GETREQ", "" + successfulRides);

					userType = result[i - 10];
					destination = result[i - 9];
					source = result[i - 7];
					date = result[i - 6];
					userName = result[i - 5];
					firstName = result[i - 4];
					lastName = result[i - 3];
					gender = result[i - 2];
					seats = result[i - 1];
					description = result[i];
					items.add(new AdInfo(
							new UserInfo(userName, firstName, lastName, gender,
									totalRides, successfulRides, null), source,
							destination, userType, fare, state, date, seats,
							description, postID, status));

				}
				userInfo.setAdInfoList(items);
				notificationList = (ListView) homeActivity
						.findViewById(R.id.notificationList);
				// create adapter
				RequestListAdapter requestListAdapter = new RequestListAdapter(
						homeActivity, items);
				// attach adapter to list view

				notificationList.setAdapter(requestListAdapter);

			} catch (Exception e) {
			}
		}

	}
}