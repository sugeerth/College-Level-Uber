package com.example.wheelshare;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment implements
		DatePickerDialog.OnDateSetListener {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		final Calendar c;
		int year, month, day;
		// if date is not set, use current date as default

		c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	public void onDateSet(DatePicker view, int year, int month, int day) {
		// Do something with the date chosen by the user
		int m = month + 1;
		activity_search.setDay(day);
		activity_search.setMonth(m);
		activity_search.setYear(year);
		activity_search.setselectDateBttnText(m + "/" + day + "/" + year);

		if ((!(activity_result.getDayStr() == null))
				&& !(activity_result.getActivity() == null)) {
			activity_result.setDay(day);
			activity_result.setMonth(m);
			activity_result.setYear(year);
			activity_result.setselectDateBttnText(m + "/" + day + "/" + year);
		}
	}

}