package com.example.wheelshare;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import android.content.Intent;
import android.os.AsyncTask;

public class RegisterFormTask extends AsyncTask<String, String, String> {

	/*
	 * params[0] = url params[1] = destination params[2] = source params[3] =
	 * departure date params[4] = fare
	 */
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		try {
			String url = params[0];

			String userName = params[1];
			String password = params[2];
			String gender = params[3];
			String firstName = params[4];
			String lastName = params[5];
			String creditCardNum = params[6];

			/* create HTTP client */
			DefaultHttpClient hc = new DefaultHttpClient();
			HttpPost postMethod = new HttpPost(url);
			/*
			 * Create name value pair list and add to it, values from post goes
			 * here <name, user input>
			 */
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("userName", userName));
			nameValuePairs.add(new BasicNameValuePair("password", password));
			nameValuePairs.add(new BasicNameValuePair("gender", gender));
			nameValuePairs.add(new BasicNameValuePair("creditCardNum",
					creditCardNum));
			nameValuePairs.add(new BasicNameValuePair("firstName", firstName));
			nameValuePairs.add(new BasicNameValuePair("lastName", lastName));

			/* Post to datastore */
			UrlEncodedFormEntity ent = new UrlEncodedFormEntity(nameValuePairs,
					HTTP.UTF_8);
			postMethod.setEntity(ent);
			HttpResponse response = hc.execute(postMethod);

			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			return rd.readLine();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected void onPostExecute(String result) {

		Intent intent = new Intent(activity_register.getContext(),
				MainActivity.class);
		activity_register.getActivity().startActivity(intent);

	}
}
