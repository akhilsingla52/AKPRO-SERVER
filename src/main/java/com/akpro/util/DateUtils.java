package com.akpro.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.akpro.util.Constants.*;

public class DateUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

	public static Long getCurrentDateAndTimeInUTCTimeZone() {
		return Calendar.getInstance(TimeZone.getTimeZone(UTC_TIMEZONE)).getTimeInMillis() / 1000;
	}
	
	public static String getUTCDate(Long time, String dateFormat) {
		if (time == null)
			return null;
		
		try {
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(Constants.UTC_TIMEZONE));
			cal.setTimeInMillis(time*1000);

			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			sdf.setLenient(false);
			
			LOGGER.debug("getDateUTCForDB : "+sdf.format(cal.getTimeInMillis()));
			return sdf.format(cal.getTimeInMillis());
		} catch (Exception e) {
			return null;
		}
	}
	
}
