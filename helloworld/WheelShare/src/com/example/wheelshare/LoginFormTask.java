package com.example.wheelshare;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public class LoginFormTask extends AsyncTask<String, String, String[]> {
	/*
	 * User info used to store the current user's information such as user name,
	 * first name, last name etc..
	 */
	UserInfo userInfo;

	@Override
	protected String[] doInBackground(String... params) {
		// TODO Auto-generated method stub
		String userName = params[0];
		String password = params[1];

		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(
					"http://helloworldws.appspot.com/login?userName="
							+ userName + "&password=" + password);
			HttpResponse response = client.execute(request);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			String temp = rd.readLine();
			temp += userName + "_";
			Log.i("error", temp);
			return temp.split("_");

		} catch (Exception e) {
		}

		return null;
	}

	@Override
	protected void onPostExecute(String[] result) {

		if (result.length == 0)
			MainActivity.setLoginErrorMsg("Please fill out both fields");
		else if (result[0].equals("SUCCESS")) {
			// getting user information
			String firstName = result[1];
			String lastName = result[2];
			String gender = result[3];
			String creditCardNum = result[4];
			int totalRides = Integer.parseInt(result[5]);
			int successfulRides = Integer.parseInt(result[6]);
			String userName = result[7];

			// Instantiate new UserInfo to store current users information
			userInfo = new UserInfo(userName, firstName, lastName, gender,
					totalRides, successfulRides, creditCardNum);

			Intent intent = new Intent(MainActivity.getContext(),
					activity_home.class);
			intent.putExtra("userInfo", userInfo);
			MainActivity.getActivity().startActivity(intent);
		} else
			MainActivity.setLoginErrorMsg("Invalid username or password");

	}

}
