package com.example.communicatemodule.eventbus;

import com.example.basicapplibdemo.R;
import com.example.commonmodule.gson.ShowGSONActivity;

import de.greenrobot.event.EventBus;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EventBusActivity extends Activity {
	private TextView txt_onEvent, txt_onEventMainThread, txt_onEventBackgroundThread, txt_onEventAsync;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eventbus);
		Button btn = (Button) findViewById(R.id.btn_opentrigger);
		txt_onEvent = (TextView) findViewById(R.id.txt_onEvent);
		txt_onEventMainThread = (TextView) findViewById(R.id.txt_onEventMainThread);
		txt_onEventBackgroundThread = (TextView) findViewById(R.id.txt_onEventBackgroundThread);
		txt_onEventAsync = (TextView) findViewById(R.id.txt_onEventAsync);

		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), EventBusTriggerActivity.class);
				startActivity(intent);
			}
		});

		EventBus.getDefault().register(this);// 事件总线--注册观察者
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);// 事件总线--注销观察者
	}

	public void onEventMainThread(FirstEvent event) {

		txt_onEventMainThread.setText("onEventMainThread收到了消息：" + event.getMsg());
	}

	// SecondEvent接收函数一
	public void onEventMainThread(SecondEvent event) {

		txt_onEventMainThread.setText("onEventMainThread收到了消息：" + event.getMsg());
	}

	// SecondEvent接收函数二
	public void onEventBackgroundThread(SecondEvent event) {
		// 此处不能直接操作，需要改为异步到主线程操作
		txt_onEventBackgroundThread.setText("onEventBackground收到了消息：" + event.getMsg());
	}

	// SecondEvent接收函数三
	public void onEventAsync(SecondEvent event) {
		txt_onEventAsync.setText("onEventAsync收到了消息：" + event.getMsg());
	}

	public void onEvent(SecondEvent event) {
		txt_onEvent.setText("OnEvent收到了消息：" + event.getMsg());
	}
	
	public void onEvent(ThirdEvent event) {
		txt_onEvent.setText("OnEvent收到了消息：" + event.getMsg());
	}
}
