package com.example.basicapplibdemo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baplib.log.BapLogConfig;
import com.baplib.log.BapLogger;
import com.baplib.log.BapStaticLogger;
import com.baplib.net.http.HttpHandler;
import com.baplib.net.netstate.BapNetStatReceiver;
import com.baplib.ui.view.DatePicker.OnDateSelectedListener;
import com.example.basicmodule.globalerr.ShowGlobalErrActivity;
import com.example.basicmodule.multilanguage.ShowMultiLanguageActivity;
import com.example.basicmodule.showlog.ShowLogActivity;
import com.example.commonmodule.gson.ShowGSONActivity;
import com.example.commonmodule.qrscan.QRScanActivity;
import com.example.commonmodule.update.UpdateActivity;
import com.example.communicatemodule.eventbus.EventBusActivity;
import com.example.communicatemodule.http.HttpActivity;
import com.example.communicatemodule.netstate.NetStateActivity;
import com.example.communicatemodule.uploadfile.UploadFileActivity;
import com.example.uimodule.badge.ShowBadge;
import com.example.uimodule.calendar.ShowCalendar;
import com.example.uimodule.charts.ChartsMainActivity;
import com.example.uimodule.cropper.CropperActivity;
import com.example.uimodule.expandhori.HoriExpandActivity;
import com.example.uimodule.horicards.ActivityHoriCards;
import com.example.uimodule.imageloader.LoadImageActivity;
import com.example.uimodule.progressbar.ProgressbarActivity;
import com.example.uimodule.special.SpecialUIActivity;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	ExpandableListView mainlistview = null;
	List<String> parent = null;
	List<List<String>> child = null;
	Map<String, List<String>> map = null;
	private BapNetStatReceiver mBapNetStatReceiver = new BapNetStatReceiver();// 监听网络状态变化

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;

		setContentView(R.layout.activity_main);
		mainlistview = (ExpandableListView) this.findViewById(R.id.main_expandablelistview);

		// 注册网络监听
		IntentFilter filter = new IntentFilter();
		filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(mBapNetStatReceiver, filter);

		initData();// 初始化显示数据
		MyAdapter adapter = new MyAdapter(this, parent, child);
		mainlistview.setAdapter(adapter);
		mainlistview.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				String str = mainlistview.getExpandableListAdapter().getChild(groupPosition, childPosition).toString();
				// Log.e("test", groupPosition+":" + childPosition + str);
				if (str.equals(MainActivity.this.getResources().getString(R.string.item_showlog))) {// "演示日志记录");
					showLog();// 测试静态日志
				}
				if (str.equals(MainActivity.this.getResources().getString(R.string.item_showglobalerr))) {// "演示全局异常捕捉"))
																											// {
					showGlobalerr();// 测试全局异常捕捉
				}
				if (str.equals(MainActivity.this.getResources().getString(R.string.item_showmultilanguage))) {// "演示多语言版本"))
																												// {
					Intent intent = new Intent(getApplicationContext(), ShowMultiLanguageActivity.class);
					startActivity(intent);
				}
				if (str.equals(MainActivity.this.getResources().getString(R.string.item_showgson))) {// "演示GSON转换"))
																										// {
					showGson();// 演示GSON转换的用法
				}
				if (str.equals(MainActivity.this.getResources().getString(R.string.item_showupdate))) {// "演示软件版本更新"))
																										// {
					showUpdate();// 演示更新软件版本
				}
				if (str.equals(MainActivity.this.getResources().getString(R.string.item_showqrscan))) {// "演示扫码识别"))
																										// {
					Intent intent = new Intent(getApplicationContext(), QRScanActivity.class);
					startActivity(intent);
				}
				if (str.equals(MainActivity.this.getResources().getString(R.string.item_showeventbus))) {// "演示事件通信总线"))
																											// {
					showEventBus();// 演示事件总线的用法
				}
				if (str.equals(MainActivity.this.getResources().getString(R.string.item_shownetstatus))) {// "演示网络状态监控"))
																											// {
					showNetState();// 演示网络状态监控
				}
				if (str.equals(MainActivity.this.getResources().getString(R.string.item_showfileupload))) {// "演示文件上传"))
																											// {
					showUpload();// 演示文件上传
				}
				if (str.equals(MainActivity.this.getResources().getString(R.string.item_showHttp))) {// "演示Http通信"))
																										// {
					Intent intent = new Intent(getApplicationContext(), HttpActivity.class);
					startActivity(intent);
				}
				if (str.equals(MainActivity.this.getResources().getString(R.string.item_showdatetimepicker))) {// "日期时间选择"))
																												// {
					initViews();
				}
				if (str.equals(MainActivity.this.getResources().getString(R.string.item_showprogressbar))) {// "演示进度条"))
																											// {
					Intent intent = new Intent(getApplicationContext(), ProgressbarActivity.class);
					startActivity(intent);
				}
				if (str.equals(MainActivity.this.getResources().getString(R.string.item_showloadimage))) {// "演示图片加载"))
																											// {
					loadImage();// 演示图片下载显示的用法
				}
				if (str.equals(MainActivity.this.getResources().getString(R.string.item_showptr))) {// "演示下拉刷新"))
																									// {
					loadPulltoRefresh();// 演示下拉刷新控件的用法
				}
				if (str.equals(MainActivity.this.getResources().getString(R.string.item_showcharts))) {// "演示图表绘制"))
																										// {
					Intent intent = new Intent(getApplicationContext(), ChartsMainActivity.class);
					startActivity(intent);
				}
				if (str.equals(MainActivity.this.getResources().getString(R.string.item_showcrop))) {// 演示图片剪裁
					Intent intent = new Intent(getApplicationContext(), CropperActivity.class);
					startActivity(intent);
				}
				if (str.equals(MainActivity.this.getResources().getString(R.string.item_showspecialui))) {// 演示特殊控件
					Intent intent = new Intent(getApplicationContext(), SpecialUIActivity.class);
					startActivity(intent);
				}
				if (str.equals(MainActivity.this.getResources().getString(R.string.item_showcalendar))) {// 演示卡片日历
					Intent intent = new Intent(getApplicationContext(), ShowCalendar.class);
					startActivity(intent);
				}
				if (str.equals(MainActivity.this.getResources().getString(R.string.item_showbadge))) {// 演示角标
					Intent intent = new Intent(getApplicationContext(), ShowBadge.class);
					startActivity(intent);
				}
				if (str.equals(MainActivity.this.getResources().getString(R.string.item_showhori))) {// 演示横向卡片
					Intent intent = new Intent(getApplicationContext(), ActivityHoriCards.class);
					startActivity(intent);
				}
				return false;
			}
		});
	}

	// 初始化数据
	public void initData() {
		parent = new ArrayList<String>();
		child = new ArrayList<List<String>>();
		parent.add(this.getResources().getString(R.string.item_basicmodule));// "底层功能模块");
		parent.add(this.getResources().getString(R.string.item_commonmodule));// "通用功能模块");
		parent.add(this.getResources().getString(R.string.item_communicatemodule));// "基础通信模块");
		parent.add(this.getResources().getString(R.string.item_uimodule));// "常用界面模块");

		List<String> list1 = new ArrayList<String>();
		list1.add(this.getResources().getString(R.string.item_showlog));// "演示日志记录");
		list1.add(this.getResources().getString(R.string.item_showglobalerr));// "演示全局异常捕捉");
		list1.add(this.getResources().getString(R.string.item_showmultilanguage));// "演示多语言版本");

		List<String> list2 = new ArrayList<String>();
		list2.add(this.getResources().getString(R.string.item_showgson));// "演示GSON转换");
		list2.add(this.getResources().getString(R.string.item_showupdate));// "演示软件版本更新");
		list2.add(this.getResources().getString(R.string.item_showqrscan));// "演示扫码识别");

		List<String> list4 = new ArrayList<String>();
		list4.add(this.getResources().getString(R.string.item_showeventbus));// "演示事件通信总线");
		list4.add(this.getResources().getString(R.string.item_shownetstatus));// "演示网络状态监控");
		list4.add(this.getResources().getString(R.string.item_showfileupload));// "演示文件上传");
		list4.add(this.getResources().getString(R.string.item_showHttp));// "演示Http通信");

		List<String> list3 = new ArrayList<String>();
		list3.add(this.getResources().getString(R.string.item_showdatetimepicker));// "日期时间选择");
		list3.add(this.getResources().getString(R.string.item_showprogressbar));// "演示进度条");
		list3.add(this.getResources().getString(R.string.item_showloadimage));// "演示图片加载");
		list3.add(this.getResources().getString(R.string.item_showptr));// "演示下拉刷新");
		list3.add(this.getResources().getString(R.string.item_showcharts));// 演示图表
		list3.add(this.getResources().getString(R.string.item_showcrop));// 演示图片剪裁
		list3.add(this.getResources().getString(R.string.item_showspecialui));// 演示特殊控件
		list3.add(this.getResources().getString(R.string.item_showcalendar));// 演示卡片日历
		list3.add(this.getResources().getString(R.string.item_showbadge));// 演示角标
		list3.add(this.getResources().getString(R.string.item_showhori));// 演示角标

		child.add(list1);
		child.add(list2);
		child.add(list4);
		child.add(list3);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mBapNetStatReceiver); // 取消网络监听
	}

	/******* 演示下拉刷新控件 *******/
	private void showPtr() {
		Intent intent = new Intent(getApplicationContext(), com.example.uimodule.pulltorefresh.LauncherActivity.class);
		startActivity(intent);
	}

	/**************/
	private void showHoriExpand() {
		Intent intent = new Intent(getApplicationContext(), HoriExpandActivity.class);
		startActivity(intent);
	}

	/**************/
	private void showUpload() {
		Intent intent = new Intent(getApplicationContext(), UploadFileActivity.class);
		startActivity(intent);
	}

	/**************/
	private void showNetState() {
		Intent intent = new Intent(getApplicationContext(), NetStateActivity.class);
		startActivity(intent);
	}

	/**************/
	private void showUpdate() {
		Intent intent = new Intent(getApplicationContext(), UpdateActivity.class);
		startActivity(intent);
	}

	/**************/
	private void loadPulltoRefresh() {
		Intent intent = new Intent(getApplicationContext(), com.example.uimodule.pulltorefresh.LauncherActivity.class);
		startActivity(intent);
	}

	/**************/
	private void loadImage() {
		Intent intent = new Intent(getApplicationContext(), LoadImageActivity.class);
		startActivity(intent);
	}

	/**************/
	private void showGson() {
		Intent intent = new Intent(getApplicationContext(), ShowGSONActivity.class);
		startActivity(intent);
	}

	/**************/
	private void showEventBus() {
		Intent intent = new Intent(getApplicationContext(), EventBusActivity.class);
		startActivity(intent);
	}

	/**************/
	private Context context;

	private void initViews() {
		com.baplib.ui.view.DatePicker dp = new com.baplib.ui.view.DatePicker(context, new OnDateSelectedListener() {

			@Override
			public void onDateSelected(String strDate) {
				Toast.makeText(context, strDate, Toast.LENGTH_SHORT).show();
			}
		});
		dp.showDateTimePicker();
	}

	/**************/
	private void showLog() {
		Intent intent = new Intent(getApplicationContext(), ShowLogActivity.class);
		startActivity(intent);
	}

	/**************/
	private void showGlobalerr() {
		Intent intent = new Intent(getApplicationContext(), ShowGlobalErrActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_about) {
			Intent intent = new Intent(this, AboutActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
