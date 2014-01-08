package com.example.wheelshare;

import java.util.Comparator;

public class SortByRatingComparator implements Comparator<AdInfo> {
	int lhsDate, rhsDate;

	@Override
	public int compare(AdInfo lhs, AdInfo rhs) {
		double success = lhs.getPosterInfo().getSuccessfulRides();
		double total = rhs.getPosterInfo().getTotalRides();
		double rating;
		if(total!=0){
			rating = 100 * success / total;
			
		}
		
		

		return 0;
	}

}
