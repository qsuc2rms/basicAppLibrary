package com.example.uimodule.badge;

import java.text.SimpleDateFormat;
import java.util.Locale;

import com.baplib.ui.calendar.CalendarCard;
import com.baplib.ui.calendar.CalendarCardPager;
import com.baplib.ui.calendar.CardGridItem;
import com.baplib.ui.calendar.OnCellItemClick;
import com.baplib.ui.view.BadgeView;
import com.example.basicapplibdemo.R;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class ShowBadge extends Activity {
	
	BadgeView badge1;
	BadgeView badge2;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_badgeview);

		View target= findViewById(R.id.img_badge);
		BadgeView badge0=new BadgeView(this,target);
		badge0.setText("OK");
		badge0.show();
		
		target= findViewById(R.id.btn_badge);
		badge0=new BadgeView(this,target);
		badge0.setText("OK");
		badge0.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);//设置位置
		badge0.show();
		target= findViewById(R.id.lay_badge);
		badge0=new BadgeView(this,target);
		badge0.setText("OK");
		badge0.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);//设置位置
		badge0.show();
		target= findViewById(R.id.lay_badge2);
		badge0=new BadgeView(this,target);
		badge0.setText("OK");
		badge0.setBackgroundResource(R.drawable.badge_ifaux);//更换背景
		badge0.setBadgePosition(BadgeView.POSITION_CENTER);//设置位置
		badge0.show();
		target= findViewById(R.id.lay_badge3);
		badge0=new BadgeView(this,target);
		badge0.setText("OK");
    	badge0.setText("Hi, There!");
    	badge0.setTextColor(Color.RED);    	
    	badge0.setTextSize(12);
    	badge0.setBadgeBackgroundColor(Color.BLUE);
		badge0.setBadgePosition(BadgeView.POSITION_BOTTOM_RIGHT);//设置位置
		badge0.show();
		
		Button btn_move= (Button) findViewById(R.id.btn_badgeMove);//晃动的角标
		badge1= new BadgeView(this, btn_move);
		badge1.setText("I'm Shaking! Click me!");    	
		badge1.show();
    	btn_move.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				TranslateAnimation anim = new TranslateAnimation(-200, 0, 0, 0);
		        anim.setInterpolator(new BounceInterpolator());
		        anim.setDuration(1000);
		        
		        badge1.toggle(false);//先隐藏，再显示
		        badge1.toggle(anim,null);
			}
		});
    	badge1.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
			Toast.makeText(ShowBadge.this, "Hey, U did click me!", Toast.LENGTH_SHORT).show();	
			}
		});
    	
    	Button btn_increment= (Button) findViewById(R.id.btn_badgeIncrement);//数字变化
		badge2= new BadgeView(this, btn_increment);
		badge2.setText("9");    	
		badge2.show();
		btn_increment.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
		        badge2.show();
		        badge2.increment(1);//此处可以定义步进长度
			}
		});
		

	}

}
