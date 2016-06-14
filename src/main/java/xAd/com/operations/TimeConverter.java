package xAd.com.operations;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeConverter {
	//@SuppressWarnings("deprecation")
	public static String timeConverter(String timeStamp) {
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm-ss:ms");
		df.setTimeZone(tz);
		Date time=new java.util.Date((long)Long.parseLong(timeStamp)*1000);
		//System.out.println(time);
		String nowAsISO = df.format(time);
		//System.out.println(nowAsISO);
		return nowAsISO;
	}
	
	public static String timeConverter() {
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm-ss:ms");
		df.setTimeZone(tz);
		String nowAsISO = df.format(new Date());
		return nowAsISO;
	}
}
	