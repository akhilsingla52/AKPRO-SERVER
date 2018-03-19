package com.akpro.util;

import java.util.Calendar;
import java.util.TimeZone;
import static com.akpro.util.Constants.*;

public class DateUtils {

	public static Long getCurrentDateAndTimeInUTCTimeZone() {
		return Calendar.getInstance(TimeZone.getTimeZone(UTC_TIMEZONE)).getTimeInMillis();
	}
	
}
