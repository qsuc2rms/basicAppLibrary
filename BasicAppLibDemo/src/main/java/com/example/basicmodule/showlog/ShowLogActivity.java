package com.example.basicmodule.showlog;

import java.util.ArrayList;
import java.util.List;

import com.baplib.common.UIUtil;
import com.baplib.log.BapLogger;
import com.baplib.log.BapStaticLogger;
import com.example.basicapplibdemo.MainActivity;
import com.example.basicapplibdemo.R;
import com.example.communicatemodule.eventbus.FirstEvent;
import com.example.communicatemodule.eventbus.SecondEvent;
import com.example.communicatemodule.eventbus.ThirdEvent;
import com.example.entity.Book;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import de.greenrobot.event.EventBus;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ShowLogActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showlog);
		Button btn_static = (Button) findViewById(R.id.btn_showStaticLog);
		Button btn_dynamic = (Button) findViewById(R.id.btn_showDynamicLog);

		// 演示静态日志
		btn_static.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(ShowLogActivity.this, "已生成静态日志，请检查日志文件或LogCat", Toast.LENGTH_SHORT).show();
				testStaticLog();
			}
		});
		// 演示动态日志
		btn_dynamic.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(ShowLogActivity.this, "已生成动态日志，请检查日志文件或LogCat", Toast.LENGTH_SHORT).show();
				testlogEntity();
			}
		});
	}

	/**************/
	private BapLogger logger;
	private void testlogEntity() {
		// 在MyApplication中统一配置，如设置日志文件夹，否则会以默认配置运行
		logger = BapLogger.getBapLogger(ShowLogActivity.class.getName());
		logger.error("baperror demo 111 in ShowLogActivity Class");
		otherClass c = new otherClass();// 测试在另一个类中能否正常打印错误所属的类
		c.testLog();
		logger.error("baperror demo 222 in ShowLogActivity Class");
	}

	/**************/
	private void testStaticLog() {
		// 在MyApplication中统一设置日志文件夹，如不设置，则以默认配置运行
		BapStaticLogger.error("bapstaticlog");
	}
}
