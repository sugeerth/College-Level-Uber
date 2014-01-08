package com.example.wheelshare;

import java.util.Calendar;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioButton;

public class activity_search extends activity_base {
	private final String URL = "http://helloworldws.appspot.com/post";
	static private Button homeBttn, postBttn, searchBttn, selectDateBttn,
			cancelDescBttn, submitDescBttn;
	EditText destination, source, fare, day, month, year, descTextField,
			SeatsEditView;
	private static String destinationStr, sourceStr, dayStr, monthStr, yearStr,
			userTypeStr, fareStr, descStr, numOfSeatsStr;
	SearchFormTask searchTask;
	private static RadioButton riderRBttn, driverRBttn;
	private UserInfo userInfo;
	private PopupWindow descWindowPopup;
	static private Activity searchActivity;
	static private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		searchActivity = this;
		context = this;
		// get user info from login screen by parcel
		Bundle b = getIntent().getExtras();
		userInfo = b.getParcelable("userInfo");
		init();
		homeBttnListener();
		searchBttnListener();
		postBttnListener();
	}

	/*
	 * Post and Search button listener
	 */
	public void postBttnListener() {

		postBttn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startPopUpWindow();
			}
		});
	}

	public void searchBttnListener() {
		searchBttn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				destinationStr = destination.getText().toString().toLowerCase();
				sourceStr = source.getText().toString().toLowerCase();
				fareStr = fare.getText().toString();
				onRadioButtonListener();

				Intent intent = new Intent(activity_search.this,
						activity_result.class);
				intent.putExtra("userInfo", userInfo);
				startActivity(intent);

			}
		});

	}

	/*
	 * Pick date dialog
	 */
	public void showDatePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getFragmentManager(), "datePicker");
	}

	/*
	 * Gender radio button
	 */
	private void onRadioButtonListener() {

		if (driverRBttn.isChecked())
			userTypeStr = "driver";
		else if (riderRBttn.isChecked())
			userTypeStr = "rider";
		else
			userTypeStr = "";
	}

	/*
	 * Menu button listener
	 */
	public void homeBttnListener() {
		homeBttn = (Button) findViewById(R.id.homeMenu);
		final Context context = this;

		homeBttn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				resetDate();
				Intent intent = new Intent(context, activity_home.class);
				intent.putExtra("userInfo", userInfo);
				startActivity(intent);
			}
		});
	}

	/*
	 * Description window pop up method
	 */
	private OnClickListener submitDescBttnListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			descStr = descTextField.getText().toString();
			destinationStr = destination.getText().toString().toLowerCase();
			sourceStr = source.getText().toString().toLowerCase();
			fareStr = fare.getText().toString();
			numOfSeatsStr = SeatsEditView.getText().toString();
			String userName = userInfo.getUserName();
			String firstName = userInfo.getFirstName();
			String lastName = userInfo.getLastName();
			String gender = userInfo.getGender();
			String date = monthStr + "/" + dayStr + "/" + yearStr;
			String totalRides = Integer.toString(userInfo.getTotalRides());
			String successfulRides = Integer.toString(userInfo
					.getSuccessfulRides());

			onRadioButtonListener();
			// check for error before opening new intent
			if (fieldErrorCheck()) {
				new PostFormTask().execute(URL, destinationStr, sourceStr,
						date, fareStr, userTypeStr, userName, firstName,
						lastName, gender, descStr, numOfSeatsStr, totalRides,
						successfulRides);
				// reset date field
				resetDate();
				descWindowPopup.dismiss();

				Intent intent = new Intent(context, activity_home.class);
				intent.putExtra("userInfo", userInfo);
				startActivity(intent);
			}

		}
	};

	private OnClickListener cancelDescBttnListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			descWindowPopup.dismiss();
		}
	};

	private void startPopUpWindow() {
		LayoutInflater inflater = (LayoutInflater) activity_search.this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View layout = inflater.inflate(R.layout.description_popup_window,
				(ViewGroup) findViewById(R.id.descriptionPopupWindow));

		descWindowPopup = new PopupWindow(layout, 800, 800, true);
		descWindowPopup.showAtLocation(layout, Gravity.CENTER, 0, 0);

		descTextField = (EditText) layout.findViewById(R.id.descTextField);
		// set desc string from EditText field

		submitDescBttn = (Button) layout.findViewById(R.id.submitDescBttn);
		cancelDescBttn = (Button) layout.findViewById(R.id.cancelDescBttn);
		// submit button listener
		submitDescBttn.setOnClickListener(submitDescBttnListener);
		cancelDescBttn.setOnClickListener(cancelDescBttnListener);

	}

	/*
	 * Error checking function
	 */
	private boolean fieldErrorCheck() {

		boolean DestinationCheck, SourceCheck, UserTypeCheck, DateCheck = true;

		if (DestinationCheck = Pattern.matches("[a-zA-Z\\s]++", destinationStr)) {
			Log.i("DESINATION CHECK", "GOOD");
		} else {
			Log.i("DESINATION CHECK", "BAD");
		}// null destination error;

		if (SourceCheck = Pattern.matches("[a-zA-Z\\s]++", sourceStr)) {
			Log.i("SOURCE CHECK", "GOOD");
		} else {
			Log.i("SOURCE CHECK", "BAD");
		}// null source error;

		if (!sourceStr.equals(destinationStr)) {
			DestinationCheck = true;
		} else {
			DestinationCheck = false;
		}// null source error;

		if (UserTypeCheck = userTypeStr != "") {
			Log.i("USER TYPE CHECK", "GOOD");
		} else {
			Log.i("USER TYPE CHECK", "BAD");
		}// null user typr error;

		if (dayStr == null || monthStr == null || yearStr == null) {
			// null date error
			Log.i("DATE", "NULL");
			DateCheck = false;
		} else {
			int date;
			date =  Integer.parseInt(monthStr) < 10 ? Integer.parseInt(yearStr + "0" + monthStr) : Integer.parseInt(yearStr + monthStr);
			Calendar c = Calendar.getInstance();
			int month = c.get(Calendar.MONTH);
			int checkdate = c.get(Calendar.YEAR) * 100 + c.get(Calendar.MONTH);// : c.get(Calendar.YEAR) * 10 + c.get(Calendar.MONTH);

			if (date - checkdate > 6) {

				// more than 6 month error
				Log.i("DATE", "MORE THAN 6 MONTHS");
				DateCheck = false;
			}
			Log.i("DATE", "GOOD");
		}

		return (DestinationCheck && SourceCheck && UserTypeCheck && DateCheck);
	}

	// init xml objects to android objects
	private void init() {
		selectDateBttn = (Button) findViewById(R.id.selectDateBttn);
		postBttn = (Button) findViewById(R.id.postBttn);
		searchBttn = (Button) findViewById(R.id.searchBttn);
		destination = (EditText) findViewById(R.id.destinationEditView);
		fare = (EditText) findViewById(R.id.fareEditView);
		source = (EditText) findViewById(R.id.startLocationEditView);
		SeatsEditView = (EditText) findViewById(R.id.SeatsEditView);
		driverRBttn = (RadioButton) findViewById(R.id.driverRadioButton);
		riderRBttn = (RadioButton) findViewById(R.id.riderRadioButton);
	}

	// reset date method
	private void resetDate() {
		dayStr = null;
		monthStr = null;
		yearStr = null;
	}

	public static Activity getActivity() {
		return searchActivity;
	}

	public static Context getContext() {
		return context;
	}

	public static void setFare(String fare) {
		fareStr = fare;
	}

	public static void setDay(int day) {

		dayStr = Integer.toString(day);
	}

	public static void setMonth(int month) {
		monthStr = Integer.toString(month);
	}

	public static void setYear(int year) {
		yearStr = Integer.toString(year);
	}

	public static void setselectDateBttnText(String date) {
		selectDateBttn.setText(date);
	}

	public static String getDestinationStr() {
		return destinationStr;
	}

	public static String getFare() {
		return fareStr;
	}

	public static String getDescStr() {
		return descStr;
	}

	public static String getNumOfSeatsStr() {
		return numOfSeatsStr;
	}

	public static String getSourceStr() {
		return sourceStr;
	}

	public static String getDayStr() {
		return dayStr;
	}

	public static String getMonthStr() {
		return monthStr;
	}

	public static String getYearStr() {
		return yearStr;
	}

	public static String getUserTypeStr() {
		return userTypeStr;
	}
}
