package com.example.wheelshare;

import java.util.Comparator;

public class SortByDateComparator implements Comparator<AdInfo> {
	String lhsDate, rhsDate, monthlhs, monthrhs, daylhs, dayrhs;

	@Override
	public int compare(AdInfo lhs, AdInfo rhs) {
		monthlhs = lhs.getMonth();
		monthrhs = rhs.getMonth();
		daylhs = lhs.getDay();
		dayrhs = rhs.getDay();

		if (monthlhs.length() == 1)
			monthlhs = "0" + monthlhs;
		if (monthrhs.length() == 1)
			monthrhs = "0" + monthrhs;

		if (daylhs.length() == 1)
			daylhs = "0" + daylhs;
		if (dayrhs.length() == 1)
			dayrhs = "0" + dayrhs;

		lhsDate = new String(lhs.getYear() + monthlhs + daylhs);
		rhsDate = new String(rhs.getYear() + monthrhs + dayrhs);

		return lhsDate.compareTo(rhsDate);

	}
}
