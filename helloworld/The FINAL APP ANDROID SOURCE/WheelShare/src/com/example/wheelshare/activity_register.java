package com.example.wheelshare;

import java.util.regex.Pattern;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class activity_register extends Activity {

	Button regBttn, backBttn;
	EditText userNameRegField, passWordRegField, cpassWordRegField,
			firstNameRegField, lastNameRegField, creditCardRegField;
	RadioButton maleRegRButton, femaleRegRButton;
	TextView userNameRegText, passWordRegText, cpassWordRegText,
			firstNameRegText, lastNameRegText, creditCardRegText,
			genderRegText;
	String userName, passWord, cpassWord, firstName, lastName, creditCardNum,
			gender;
	static private Activity regActivity;
	static private Context context;
	final String URL = "http://helloworldws.appspot.com/register";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		init();
		registerBttnListener();
		backBttnListener();
	}

	public void registerBttnListener() {

		regBttn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// get text from EditText fields
				userName = userNameRegField.getText().toString().toLowerCase();
				passWord = passWordRegField.getText().toString();
				cpassWord = cpassWordRegField.getText().toString();
				firstName = firstNameRegField.getText().toString();
				lastName = lastNameRegField.getText().toString();
				creditCardNum = creditCardRegField.getText().toString();
				onRadioButtonListener();

				/*
				 * String url = params[0]; String userName = params[1] String
				 * password = params[2] String gender = params[3] String
				 * firstName = params[4] String lastName = params[5] String
				 * creditCardNum = params[6]
				 */
				if (fieldErrorCheck()) {
					new RegisterFormTask().execute(URL, userName, passWord,
							gender, firstName, lastName, creditCardNum);
				}
			}
		});

	}

	private void onRadioButtonListener() {

		if (maleRegRButton.isChecked())
			gender = "m";
		else if (femaleRegRButton.isChecked())
			gender = "f";
		else
			gender = "";
	}

	private void init() {
		regActivity = this;
		context = this;
		regBttn = (Button) findViewById(R.id.regBttn);
		backBttn = (Button) findViewById(R.id.backBttn);
		userNameRegField = (EditText) findViewById(R.id.userNameRegField);
		passWordRegField = (EditText) findViewById(R.id.passWordRegField);
		cpassWordRegField = (EditText) findViewById(R.id.cpassWordRegField);
		firstNameRegField = (EditText) findViewById(R.id.firstNameRegField);
		lastNameRegField = (EditText) findViewById(R.id.lastNameRegField);
		creditCardRegField = (EditText) findViewById(R.id.creditCardNumRegField);

		userNameRegText = (TextView) findViewById(R.id.userNameRegText);
		passWordRegText = (TextView) findViewById(R.id.passWordRegText);
		cpassWordRegText = (TextView) findViewById(R.id.cpassWordRegText);
		firstNameRegText = (TextView) findViewById(R.id.firstNameRegText);
		lastNameRegText = (TextView) findViewById(R.id.lastNameRegText);
		creditCardRegText = (TextView) findViewById(R.id.creditCardRegText);
		genderRegText = (TextView) findViewById(R.id.genderRegText);

		maleRegRButton = (RadioButton) findViewById(R.id.maleRegRadioButton);
		femaleRegRButton = (RadioButton) findViewById(R.id.femaleRegRadioButton);

	}

	private boolean fieldErrorCheck() {
		boolean userNameCheck, passWordCheck, cpassWordCheck, firstNameCheck, lastNameCheck, genderCheck;

		if (userNameCheck = Pattern.matches("^[a-z0-9_-]{3,15}$", userName)) {
			userNameRegText.setTextColor(Color.BLACK);
			userNameCheck = true;
		} else {
			userNameRegText.setTextColor(getResources().getColor(
					R.color.textErrorColor));
			userNameCheck = false;
		}

		if (passWordCheck = Pattern.matches(
				"((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})",
				passWord)) {
			passWordRegText.setTextColor(Color.BLACK);
			passWordCheck = true;
		} else {
			passWordRegText.setTextColor(getResources().getColor(
					R.color.textErrorColor));
			passWordCheck = false;
		}

		if (cpassWordCheck = (cpassWord.equals(passWord))) {
			cpassWordRegText.setTextColor(Color.BLACK);
			cpassWordCheck = true;
		} else {
			cpassWordRegText.setTextColor(getResources().getColor(
					R.color.textErrorColor));
			cpassWordCheck = false;
		}

		if (firstNameCheck = Pattern.matches("^[a-z0-9_-]{3,15}$", firstName)) {
			firstNameRegText.setTextColor(Color.BLACK);
			firstNameCheck = true;
		} else {
			firstNameRegText.setTextColor(getResources().getColor(
					R.color.textErrorColor));
			firstNameCheck = false;
		}

		if (lastNameCheck = Pattern.matches("^[a-z0-9_-]{3,15}$", lastName)) {
			lastNameRegText.setTextColor(Color.BLACK);
			lastNameCheck = true;
		} else {
			lastNameRegText.setTextColor(getResources().getColor(
					R.color.textErrorColor));
			lastNameCheck = false;
		}

		if (genderCheck = gender != "") {
			genderRegText.setTextColor(Color.BLACK);
			genderCheck = true;
		} else {
			genderRegText.setTextColor(getResources().getColor(
					R.color.textErrorColor));
			genderCheck = false;
		}

		// all checks pass, return true
		return (userNameCheck && firstNameCheck && lastNameCheck
				&& passWordCheck && cpassWordCheck && genderCheck);

	}

	public void backBttnListener() {

		final Context context = this;

		backBttn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(context, MainActivity.class);
				startActivity(intent);
			}
		});
	}

	static Activity getActivity() {
		return regActivity;
	}

	static Context getContext() {
		return context;
	}
}
