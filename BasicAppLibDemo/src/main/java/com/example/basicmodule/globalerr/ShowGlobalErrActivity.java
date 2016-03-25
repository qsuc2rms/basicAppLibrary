package com.example.basicmodule.globalerr;

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

public class ShowGlobalErrActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showglobalerr);
		Button btn = (Button) findViewById(R.id.btn_showGlobalErr);

		// 演示静态日志
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				testGlobalerr();
			}
		});

	}

	/**************/
	private String s;

	private void testGlobalerr() {
		s.equals("make a global err");// 制造一个error，测试全局异常捕捉功能
	}
}
