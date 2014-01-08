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

public class ecsrow extends AsyncTask<String, String, String> {
	/*
	 * params[0] = url params[1] = destination params[2] = source params[3] =
	 * departure date params[4] = fare
	 */
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		try {

			/* create HTTP client */
			DefaultHttpClient hc = new DefaultHttpClient();
			HttpPost postMethod = new HttpPost(
					"http://helloworldws.appspot.com/escrow");
			/*
			 * Create name value pair list and add to it, values from post goes
			 * here <name, user input>
			 */
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("destination", "fdsaf"));

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
