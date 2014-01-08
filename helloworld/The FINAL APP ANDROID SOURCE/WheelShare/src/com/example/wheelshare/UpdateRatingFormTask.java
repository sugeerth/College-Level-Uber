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
import android.os.AsyncTask;

public class UpdateRatingFormTask extends AsyncTask<String, String, String> {

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		try {

			String userName = params[0];
			String rating = params[1];

			/* create HTTP client */
			DefaultHttpClient hc = new DefaultHttpClient();
			HttpPost postMethod = new HttpPost(
					"http://helloworldws.appspot.com/updaterating");

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

			nameValuePairs.add(new BasicNameValuePair("userName", userName));
			nameValuePairs.add(new BasicNameValuePair("rating", rating));

			UrlEncodedFormEntity ent = new UrlEncodedFormEntity(nameValuePairs,
					HTTP.UTF_8);

			postMethod.setEntity(ent);
			HttpResponse response = hc.execute(postMethod);
			HttpEntity resEntity = response.getEntity();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
