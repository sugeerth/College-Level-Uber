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
import android.util.Log;

public class RequestFormTask extends AsyncTask<String, String, String> {

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		try {
			String url = params[0];
			String posterUserName = params[1];
			String requesterUserName = params[2];
			String postID = params[3];

			/* create HTTP client */
			DefaultHttpClient hc = new DefaultHttpClient();
			HttpPost postMethod = new HttpPost(url);
			/*
			 * Create name value pair list and add to it, values from post goes
			 * here <name, user input>
			 */
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("Requester Username",
					requesterUserName));
			nameValuePairs.add(new BasicNameValuePair("Poster Username",
					posterUserName));
			nameValuePairs.add(new BasicNameValuePair("postID", postID));

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

}
