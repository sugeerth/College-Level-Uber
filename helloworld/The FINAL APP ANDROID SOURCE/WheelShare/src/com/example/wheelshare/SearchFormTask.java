package com.example.wheelshare;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SearchFormTask extends AsyncTask<String, String, String[]> {
	private static final String TAG = "SearchFormTask";
	Context context;

	public SearchFormTask(Context context) {
		this.context = context.getApplicationContext();
	}

	@Override
	protected String[] doInBackground(String... params) {

		String destination = params[0];
		String source = params[1];
		String day = params[2];
		String month = params[3];
		String year = params[4];
		String userType = params[5];
		String firstName = params[6];
		String lastName = params[7];
		String gender = params[8];
		String fare = params[9];
		String date = "";

		// servlet checks for date == "" and fare == ""
		if (month != null && day != null && year != null)
			date = month + "/" + day + "/" + year;

		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet request;

			if (day == null)
				Log.i("Testing", "Date is null");
			if (fare == null)
				Log.i("Testing", "fare is null");
			if (fare.equals(""))
				Log.i("Testing", "fare is empty string");

			Log.i("FARE IS: ", fare);

			request = new HttpGet(
					"http://helloworldws.appspot.com/search?destination="
							+ destination + "&source=" + source + "&date="
							+ date + "&userType=" + userType + "&fare=" + fare);

			HttpResponse response = client.execute(request);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String temp = rd.readLine();
			Log.i(TAG, temp);
			String[] results = temp.split("_");
			return results;

		} catch (Exception e) {
		}
		return null;
	}

	@Override
	protected void onPostExecute(String[] result) {
		List<AdInfo> items = new ArrayList<AdInfo>();
		int fare, totalRides, successfulRides;
		long postID;
		String destination, source, date, userName, firstName, lastName, gender, seats, description, userType;

		try {
			for (int i = 13; i < result.length + 13; i = i + 14) {

				fare = Integer.parseInt(result[i - 8]);
				postID = Long.parseLong(result[i - 13]);
				totalRides = Integer.parseInt(result[i - 12]);
				successfulRides = Integer.parseInt(result[i - 11]);

				Log.i("TOTAL RIDE IN SEARCHFORM", "" + totalRides);
				Log.i("SUCCESS IN SEARCHFORM", "" + successfulRides);
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
				items.add(new AdInfo(new UserInfo(userName, firstName,
						lastName, gender, totalRides, successfulRides,
						null), source, destination, userType, fare, 1, date,
						seats, description, postID, null));
			}

			// pass back the search results to activity_result screen
			activity_result.setSearchResults(items);

		} catch (Exception e) {
			TextView noResultTextView;
			// No search results so display the post info TextView
			noResultTextView = activity_result.getNoResultTextView();
			LinearLayout noResultLayout = activity_result.getNoResultLayout();
			Button postBttn = activity_result.getPostButton();
			LinearLayout sortLinearLayout = activity_result
					.getSortLinearLayout(); // Display post info
			postBttn.setVisibility(View.VISIBLE);
			noResultTextView.setVisibility(View.VISIBLE);
			noResultLayout.setVisibility(View.VISIBLE);
			sortLinearLayout.setVisibility(View.INVISIBLE);

		}

	}
}
