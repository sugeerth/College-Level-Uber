package com.example.wheelshare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class activity_base extends Activity {

	private final String TAG = "Base Activity";
	final Context context = this;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intentHome = new Intent(context, activity_home.class);
		Intent intentSearch = new Intent(context, activity_search.class);
		Intent intentPost = new Intent(context, activity_post.class);

		switch (item.getItemId()) {
		case R.id.homeMenu:
			startActivity(intentHome);
			return true;
		case R.id.postMenu:
			startActivity(intentPost);
			return true;
		case R.id.searchMenu:
			startActivity(intentSearch);
			return true;

		case R.id.settingMenu:
			Log.i(TAG, "Settings clicked");
			return true;
		default:
			super.onOptionsItemSelected(item);
		}
		return false;

	}

}
