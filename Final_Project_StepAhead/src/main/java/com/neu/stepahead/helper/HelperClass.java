package com.neu.stepahead.helper;

import java.sql.Date;

public class HelperClass {
	public static Date getCurrentDate() {
		java.util.Date today = new java.util.Date();
		return new Date(today.getTime());
	}
}
