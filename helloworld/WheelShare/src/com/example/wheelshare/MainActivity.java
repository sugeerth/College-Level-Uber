package com.example.wheelshare;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	// Variables
	Button signInBttn, registerBttn;
	EditText userNameField, passWordField;
	private static String userName, passWord;
	static TextView loginErrorMsg;
	static private Context context;
	static private Activity mainActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		signInButtonListener(); // this links the sign in button with
								// activity_home once the user logs in
		registerButtonListener();
		new ecsrow().execute();
	}

	public void signInButtonListener() {

		signInBttn = (Button) findViewById(R.id.signInBttn);

		/* This is where we still check for correct user login, accnt/pw */
		signInBttn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				userName = userNameField.getText().toString().toLowerCase();
				passWord = passWordField.getText().toString();
				new LoginFormTask().execute(userName, passWord);
			}
		});
	}

	public void registerButtonListener() {
		final Context context = this;
		registerBttn = (Button) findViewById(R.id.registerBttn);

		registerBttn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(context, activity_register.class);
				startActivity(intent);
			}
		});
	}

	private void init() {
		mainActivity = this;
		context = this;
		loginErrorMsg = (TextView) findViewById(R.id.loginErrorMsg);
		userNameField = (EditText) findViewById(R.id.userNameField);
		passWordField = (EditText) findViewById(R.id.passWordField);

	}

	public static void setLoginErrorMsg(String msg) {
		loginErrorMsg.setText(msg);
	}

	public static Activity getActivity() {
		return mainActivity;
	}

	public static Context getContext() {
		return context;
	}

	public static String getUserName() {
		return userName;
	}
}
