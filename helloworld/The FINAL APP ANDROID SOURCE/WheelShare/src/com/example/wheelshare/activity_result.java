package com.example.wheelshare;

import java.util.Collections;
import java.util.List;

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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

public class activity_result extends activity_base {

	private final String URL = "http://helloworldws.appspot.com/post";
	private static Button backBttn, selectDateBttn, submitDescBttn,
			cancelDescBttn;
	private static TextView noResult, sortByDateBttn, sortByRatingBttn,
			sortBySeatsBttn, sortByFareBttn, descTextField;
	EditText destination, source, fare, SeatsEditView;
	private static String destinationStr, sourceStr, dayStr, monthStr, yearStr,
			fareStr, userTypeStr, numOfSeatsStr, descStr;
	static ListView searchResults;
	private static Context context;
	private static Activity resultActivity;
	private static LinearLayout noResultLayout, sortLinearLayout;
	private static Button postBttn;
	private static RadioButton riderRBttn, driverRBttn;
	public static UserInfo userInfo;
	private static ListView inboxListView;
	private static List<AdInfo> items;
	private static PopupWindow descWindowPopup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		// get parcel from activity_search
		Bundle b = getIntent().getExtras();
		if (b != null) {
			Log.i("user", "userInfo is NOT null");
			userInfo = b.getParcelable("userInfo");

		}

		init();
		backBttnListener();
		postBttnListener();

		// sort listeners
		sortByDateBttn();
		sortByRatingBttn();
		sortByFareBttn();
		sortBySeatsBttn();

		// get date from dateFragment
		dayStr = activity_search.getDayStr();
		monthStr = activity_search.getMonthStr();
		yearStr = activity_search.getYearStr();
		// search the query
		try {
			SearchFormTask searchTask = new SearchFormTask(context);
			searchTask.execute(destinationStr, sourceStr, dayStr, monthStr,
					yearStr, userTypeStr, userInfo.getFirstName(),
					userInfo.getLastName(), userInfo.getGender(),
					activity_search.getFare());
			dayStr = null;
			monthStr = null;
			yearStr = null;
			activity_search.setFare("");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void backBttnListener() {

		backBttn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}

	public void postBttnListener() {

		postBttn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startPopUpWindow();
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
			destinationStr = destination.getText().toString();
			sourceStr = source.getText().toString();
			fareStr = fare.getText().toString();
			numOfSeatsStr = SeatsEditView.getText().toString();
			String userName = userInfo.getUserName();
			String firstName = userInfo.getFirstName();
			String lastName = userInfo.getLastName();
			String gender = userInfo.getGender();
			// String creditCardNum = userInfo.getCreditCardNum();
			String date = monthStr + "/" + dayStr + "/" + yearStr;
			String totalRides = Integer.toString(userInfo.getTotalRides());
			String successfulRides = Integer.toString(userInfo
					.getSuccessfulRides());

			onRadioButtonListener();

			new PostFormTask()
					.execute(URL, destinationStr, sourceStr, date, fareStr,
							userTypeStr, userName, firstName, lastName, gender,
							descStr, numOfSeatsStr, totalRides, successfulRides);

			descWindowPopup.dismiss();

			Intent intent = new Intent(context, activity_home.class);
			intent.putExtra("userInfo", userInfo);
			startActivity(intent);

		}
	};

	private OnClickListener cancelDescBttnListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			descWindowPopup.dismiss();
		}
	};

	private void startPopUpWindow() {
		LayoutInflater inflater = (LayoutInflater) activity_result.this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View layout = inflater.inflate(R.layout.description_popup_window,
				(ViewGroup) findViewById(R.id.descriptionPopupWindow));

		descWindowPopup = new PopupWindow(layout, 400, 350, true);
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
	 * Listener for sorting buttons
	 */
	public void sortByDateBttn() {

		sortByDateBttn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Collections.sort(items, new SortByDateComparator());
				sortByDateBttn.setBackgroundColor(getResources().getColor(
						R.color.sortOnClickColor));
				sortByRatingBttn.setBackgroundColor(getResources().getColor(
						R.color.BLACK));
				sortByFareBttn.setBackgroundColor(getResources().getColor(
						R.color.BLACK));
				sortBySeatsBttn.setBackgroundColor(getResources().getColor(
						R.color.BLACK));
				SearchListAdapter searchListAdapter = new SearchListAdapter(
						resultActivity, items);
				// attach adapter to list view
				inboxListView.setAdapter(searchListAdapter);
			}
		});
	}

	public void sortByRatingBttn() {

		sortByRatingBttn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Collections.sort(items, new SortByDateComparator());
				sortByDateBttn.setBackgroundColor(getResources().getColor(
						R.color.BLACK));
				sortByRatingBttn.setBackgroundColor(getResources().getColor(
						R.color.sortOnClickColor));
				sortByFareBttn.setBackgroundColor(getResources().getColor(
						R.color.BLACK));
				sortBySeatsBttn.setBackgroundColor(getResources().getColor(
						R.color.BLACK));

			}
		});
	}

	public void sortByFareBttn() {

		sortByFareBttn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Collections.sort(items, new SortByFareComparator());
				sortByDateBttn.setBackgroundColor(getResources().getColor(
						R.color.BLACK));
				sortByRatingBttn.setBackgroundColor(getResources().getColor(
						R.color.BLACK));
				sortByFareBttn.setBackgroundColor(getResources().getColor(
						R.color.sortOnClickColor));
				sortBySeatsBttn.setBackgroundColor(getResources().getColor(
						R.color.BLACK));
				SearchListAdapter searchListAdapter = new SearchListAdapter(
						resultActivity, items);
				// attach adapter to list view
				inboxListView.setAdapter(searchListAdapter);

			}
		});
	}

	public void sortBySeatsBttn() {

		sortBySeatsBttn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Collections.sort(items, new SortBySeatComparator());
				sortByDateBttn.setBackgroundColor(getResources().getColor(
						R.color.BLACK));
				sortByRatingBttn.setBackgroundColor(getResources().getColor(
						R.color.BLACK));
				sortByFareBttn.setBackgroundColor(getResources().getColor(
						R.color.BLACK));
				sortBySeatsBttn.setBackgroundColor(getResources().getColor(
						R.color.sortOnClickColor));

			}
		});
	}

	public void showDatePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getFragmentManager(), "datePicker");
	}

	private void onRadioButtonListener() {

		if (driverRBttn.isChecked())
			userTypeStr = "driver";
		else if (riderRBttn.isChecked())
			userTypeStr = "rider";
		else
			userTypeStr = "";
	}

	static void setSearchResults(List<AdInfo> results) {
		items = results;
		inboxListView = (ListView) resultActivity
				.findViewById(R.id.notificationList);
		// create adapter
		SearchListAdapter searchListAdapter = new SearchListAdapter(
				resultActivity, results);
		// attach adapter to list view
		inboxListView.setOnItemClickListener(clickLister);
		inboxListView.setAdapter(searchListAdapter);
	}

	// click listener
	static private AdapterView.OnItemClickListener clickLister = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> adapterView, View view, int pos,
				long l) {
			try {
				// get AdInfo from position in list and pass to result_detail
				// activity
				Intent intent = new Intent(resultActivity,
						activity_detail.class);
				AdInfo adInfo = getResults().get(pos);

				intent.putExtra("requesterUserName", userInfo.getUserName());
				intent.putExtra("adInfo", adInfo);
				context.startActivity(intent);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	public static Activity getActivity() {
		return resultActivity;
	}

	public static Context getContext() {
		return context;
	}

	public static TextView getNoResultTextView() {
		return noResult;
	}

	public static LinearLayout getNoResultLayout() {
		return noResultLayout;
	}

	public static LinearLayout getSortLinearLayout() {
		return sortLinearLayout;
	}

	public static Button getPostButton() {
		return postBttn;
	}

	public static String getUserTypeStr() {
		return userTypeStr;
	}

	private void init() {
		context = this;
		resultActivity = this;
		destination = (EditText) findViewById(R.id.destinationEditView);
		source = (EditText) findViewById(R.id.startLocationEditView);
		fare = (EditText) findViewById(R.id.fareEditView);
		SeatsEditView = (EditText) findViewById(R.id.SeatsEditView);
		noResultLayout = (LinearLayout) findViewById(R.id.noResultLayout);
		sortLinearLayout = (LinearLayout) findViewById(R.id.sortLinearLayout);
		selectDateBttn = (Button) findViewById(R.id.selectDateBttn);
		backBttn = (Button) findViewById(R.id.backBttn);
		postBttn = (Button) findViewById(R.id.postBttn);
		noResult = (TextView) findViewById(R.id.noResultTextView);
		driverRBttn = (RadioButton) findViewById(R.id.driverRadioButton);
		riderRBttn = (RadioButton) findViewById(R.id.riderRadioButton);
		sortByDateBttn = (TextView) findViewById(R.id.sortByDateBttn);
		sortByRatingBttn = (TextView) findViewById(R.id.sortByRatingBttn);
		sortBySeatsBttn = (TextView) findViewById(R.id.sortBySeatsBttn);
		sortByFareBttn = (TextView) findViewById(R.id.sortByFareBttn);
		// Hide these views if search is success
		noResult.setVisibility(View.GONE);
		noResultLayout.setVisibility(View.GONE);
		postBttn.setVisibility(View.GONE);
		// get EditText from previous search field
		destinationStr = activity_search.getDestinationStr();
		sourceStr = activity_search.getSourceStr();
		dayStr = activity_search.getDayStr();
		monthStr = activity_search.getMonthStr();
		yearStr = activity_search.getYearStr();
		userTypeStr = activity_search.getUserTypeStr();
		descStr = activity_search.getDescStr();
		numOfSeatsStr = activity_search.getNumOfSeatsStr();

		// set EditText from previous search field
		destination.setText(destinationStr);
		source.setText(sourceStr);
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

	public static String getDayStr() {
		return dayStr;
	}

	public static String getMonthStr() {
		return monthStr;
	}

	public static String getYearStr() {
		return yearStr;
	}

	public static List<AdInfo> getResults() {
		return items;
	}

}
