package com.baplib.common;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class UIUtil {
	  private static final String TAG = "UIUtil";

	 
	public static <T>  T getView(View root, int viewResId) {
		View v = root.findViewById(viewResId);
		try {
			T t = (T) v;
			return t;
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			return null;
		}
	}
	
	public static <T>  T getView(Activity root, int viewResId) {
		View v = root.findViewById(viewResId);
		try {
			T t = (T) v;
			return t;
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			return null;
		}
	}
	
	public static String getCurDateTime() {
		Calendar c=Calendar.getInstance(Locale.CHINA);
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str =df.format(c.getTime());
		return str;
	}
	
	public static String getCurDate() {
		Calendar c=Calendar.getInstance(Locale.CHINA);
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String str =df.format(c.getTime());
		return str;
	}
	
	public static String getCurTime() {
		Calendar c=Calendar.getInstance(Locale.CHINA);
		SimpleDateFormat df=new SimpleDateFormat("HH:mm:ss");
		String str =df.format(c.getTime());
		return str;
	}
	
	public static String getNextHour() {
		Calendar c=Calendar.getInstance(Locale.CHINA);
		c.add(Calendar.HOUR_OF_DAY, 1);
		SimpleDateFormat df=new SimpleDateFormat("HH:mm:ss");
		String str =df.format(c.getTime());
		return str;
	}

	public static long getTime(int seconds){
		return System.currentTimeMillis()+seconds*1000;
	}
	
	public static String getStandardTime(long time){
		String format="yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat df=new SimpleDateFormat(format);
		Date dt=new Date(time);
		String str=df.format(dt);
		return str;
	}
}
