package com.example.wheelshare;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;

public class PostFormTask extends AsyncTask<String, String, String> {
	/*
	 * params[0] = url params[1] = destination params[2] = source params[3] =
	 * departure date params[4] = fare
	 */
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		try {
			String url = params[0];

			String destination = params[1];
			String source = params[2];
			String departureDate = params[3];
			String fare = params[4];
			String userType = params[5];
			String userName = params[6];
			String firstName = params[7];
			String lastName = params[8];
			String gender = params[9];
			String description = params[10];
			String numOfSeats = params[11];
			String totalRides = params[12];
			String successfulRides = params[13];

			/* create HTTP client */
			DefaultHttpClient hc = new DefaultHttpClient();
			HttpPost postMethod = new HttpPost(url);
			/*
			 * Create name value pair list and add to it, values from post goes
			 * here <name, user input>
			 */
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("destination",
					destination));
			nameValuePairs.add(new BasicNameValuePair("source", source));
			nameValuePairs.add(new BasicNameValuePair("date", departureDate));
			nameValuePairs.add(new BasicNameValuePair("fare", fare));
			nameValuePairs.add(new BasicNameValuePair("userType", userType));
			nameValuePairs.add(new BasicNameValuePair("userName", userName));
			nameValuePairs.add(new BasicNameValuePair("firstName", firstName));
			nameValuePairs.add(new BasicNameValuePair("lastName", lastName));
			nameValuePairs.add(new BasicNameValuePair("gender", gender));
			nameValuePairs.add(new BasicNameValuePair("seats", numOfSeats));
			nameValuePairs.add(new BasicNameValuePair("description",
					description));
			nameValuePairs
					.add(new BasicNameValuePair("total rides", totalRides));
			nameValuePairs.add(new BasicNameValuePair("successful rides",
					successfulRides));

			/* Post to datastore */
			UrlEncodedFormEntity ent = new UrlEncodedFormEntity(nameValuePairs,
					HTTP.UTF_8);
			postMethod.setEntity(ent);
			HttpResponse response = hc.execute(postMethod);
			HttpEntity resEntity = response.getEntity();

			if (resEntity != null) {
				Log.i("RESPONSE", EntityUtils.toString(resEntity));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
