package com.example.wheelshare;

import com.example.wheelshare.PostFormTask;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_post extends activity_base {

	final String url = "http://helloworldws.appspot.com/post";
	Button homeBttn, searchBttn, settingBttn, confirmBttn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post);
		homeBttnListener();
		searchBttnListener();
		confirmBttnListener();
	}

	public void confirmBttnListener() {
		confirmBttn = (Button) findViewById(R.id.confirmPostBttn);
		confirmBttn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				new PostFormTask().execute(url);
			}
		});
	}

	public void homeBttnListener() {
		homeBttn = (Button) findViewById(R.id.homeMenu);
		final Context context = this;

		homeBttn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(context, activity_home.class);
				startActivity(intent);
			}
		});
	}

	public void searchBttnListener() {
		searchBttn = (Button) findViewById(R.id.searchMenu);
		final Context context = this;

		searchBttn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(context, activity_search.class);
				startActivity(intent);
			}
		});
	}

}
