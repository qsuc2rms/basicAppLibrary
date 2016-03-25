package com.example.communicatemodule.netstate;

import java.util.ArrayList;
import java.util.List;

import com.baplib.net.netstate.BapNetStatReceiver;
import com.baplib.net.netstate.NetStatEvent;
import com.baplib.net.netstate.NetStates;
import com.example.basicapplibdemo.R;
import de.greenrobot.event.EventBus;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class NetStateActivity extends Activity {
	private TextView txt_NetState;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_netstate);

		txt_NetState = (TextView) findViewById(R.id.txt_netstate);

		EventBus.getDefault().register(this);//事件总线--注册网络状态观察者
		
		showNetState(BapNetStatReceiver.getConnectedType(this));//初次加载即显示当前联网状态
	}
	

	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);//事件总线--注销网络状态观察者
	}
	/*
	 * 监控网络状态变化事件
	 */
	public void onEventMainThread(NetStatEvent event) {
		showNetState(event.getNetStat());
	}
	
	private void showNetState(int type){
		switch (type) {
		case NetStates.TYPE_DISCONNECTED:
			txt_NetState.setText("Disconnected!");
			break;
		case NetStates.TYPE_WIFI:
			txt_NetState.setText("WIFI");
			break;
		case NetStates.TYPE_MOBILE:
			txt_NetState.setText("MOBILE");
			break;
		default:
			break;
		}
	}
}
