package com.example.wheelshare;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class gps {
	
	
	public void  gps1() {
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

		// Define a listener that responds to location updates
		LocationListener locationListener = new LocationListener() {
		    public void onLocationChanged1(Location location) {
		      // Called when a new location is found by the network location provider.
		      makeUseOfNewLocation(location);
		    }

		    public void onStatusChanged1(String provider, int status, Bundle extras) {}

		    public void onProviderEnabled(String provider) {}

		    public void onProviderDisabled(String provider) {}

			@Override
			public void onLocationChanged(Location arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
				// TODO Auto-generated method stub
				
			}
		  };

		// Register the listener with the Location Manager to receive location updates
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
		String locationProvider = LocationManager.NETWORK_PROVIDER;
		// Or, use GPS location data:
		// String locationProvider = LocationManager.GPS_PROVIDER;

		locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);
		
		locationManager.removeUpdates(locationListener);
	}

	private LocationManager getSystemService(String locationService) {
		// TODO Auto-generated method stub
		return null;
	}

	protected void makeUseOfNewLocation(Location location) {
		// TODO Auto-generated method stub
		
	}

}
