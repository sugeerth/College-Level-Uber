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

public class UpdateEscrowFormTask extends AsyncTask<String, String, String> {

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		try {
			String amount = params[0];
			String stateNum = params[1];

			Log.i("STATE IN ESCROW", stateNum);
			Log.i("amount in escrow", amount);

			/* create HTTP client */
			DefaultHttpClient hc = new DefaultHttpClient();
			HttpPost postMethod = new HttpPost(
					"http://helloworldws.appspot.com/updateescrow");

			/*
			 * Create name value pair list and add to it, values from post goes
			 * here <name, user input>
			 */
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("stateNum", stateNum));
			nameValuePairs.add(new BasicNameValuePair("amount", amount));

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
