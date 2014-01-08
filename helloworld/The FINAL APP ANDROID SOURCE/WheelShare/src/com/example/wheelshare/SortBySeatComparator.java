package com.example.wheelshare;

import java.util.Comparator;

public class SortBySeatComparator implements Comparator<AdInfo> {

	@Override
	public int compare(AdInfo lhs, AdInfo rhs) {

		int l = Integer.parseInt(lhs.getSeats());
		int r = Integer.parseInt(rhs.getSeats());
		if (l > r)
			return 1;
		else if (l < r)
			return -1;
		else
			return 0;

	}
}