package com.example.wheelshare;

import java.util.Comparator;

public class SortByFareComparator implements Comparator<AdInfo> {
	int lhsDate, rhsDate;

	@Override
	public int compare(AdInfo lhs, AdInfo rhs) {

		if (lhs.getFare() < rhs.getFare())
			return -1;
		else if (lhs.getFare() > rhs.getFare())
			return 1;
		else
			return 0;
	}
}
