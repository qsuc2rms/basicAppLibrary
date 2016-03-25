package com.example.uimodule.calendar;

import java.text.SimpleDateFormat;
import java.util.Locale;

import com.baplib.ui.calendar.CalendarCard;
import com.baplib.ui.calendar.CalendarCardPager;
import com.baplib.ui.calendar.CardGridItem;
import com.baplib.ui.calendar.OnCellItemClick;
import com.example.basicapplibdemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;



public class ShowCalendar extends Activity {
	
	private CalendarCard mCalendarCard;
	private CalendarCardPager mCalendarCardpager;
	private TextView mTextViewCard;
	private TextView mTextViewCardPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar);
		mCalendarCard = (CalendarCard)findViewById(R.id.cal_card);
		mCalendarCardpager = (CalendarCardPager)findViewById(R.id.cal_cardpager);

		mTextViewCard = (TextView)findViewById(R.id.txt_calendarcard);
		mTextViewCardPager = (TextView)findViewById(R.id.txt_calendarcardpager);
		
		mCalendarCard.setOnCellItemClick(new OnCellItemClick() {
			@Override
			public void onCellClick(View v, CardGridItem item) {
				mTextViewCard.setText(getResources().getString(R.string.sel_date, new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(item.getDate().getTime())));
			}
		});
		
		mCalendarCardpager.setOnCellItemClick(new OnCellItemClick() {
			@Override
			public void onCellClick(View v, CardGridItem item) {
				mTextViewCardPager.setText(getResources().getString(R.string.sel_date, new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(item.getDate().getTime())));
			}
		});
	}

}
