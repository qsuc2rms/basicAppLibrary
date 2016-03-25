package com.example.communicatemodule.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.baplib.net.http.HttpHandler;
import com.baplib.net.netstate.BapNetStatReceiver;
import com.baplib.net.netstate.NetStatEvent;
import com.baplib.net.netstate.NetStates;
import com.example.basicapplibdemo.R;
import de.greenrobot.event.EventBus;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HttpActivity extends Activity {
	private TextView txt_get, txt_post, txt_json;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showhttp);

		txt_get = (TextView) findViewById(R.id.txt_get);
		txt_post = (TextView) findViewById(R.id.txt_post);
		txt_json = (TextView) findViewById(R.id.txt_json);

		Button btn_get = (Button) findViewById(R.id.btn_first_event);
		Button btn_post = (Button) findViewById(R.id.btn_second_event);
		Button btn_json = (Button) findViewById(R.id.btn_third_event);

		btn_get.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				testHttpHandler();
			}
		});
		btn_post.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				testHttpPostHandler();
			}
		});
		btn_json.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				testHttpJsonHandler();
			}
		});
	}

	/**************/
	private void testHttpHandler() {
		StringRequest stringRequest = new StringRequest("http://www.baidu.com", new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				txt_get.setText(response);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				txt_get.setText(error.getMessage());
			}
		});

		HttpHandler.add(stringRequest);// HttpHandler已经在BapApplication中进行过初始化，所以可以直接使用
	}

	private void testHttpPostHandler() {
		StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://www.baidu.com",
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						txt_post.setText("response -> " + response);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						txt_post.setText(error.getMessage());
					}
				}) {
			@Override
			protected Map<String, String> getParams() {
				// 在这里设置需要post的参数
				Map<String, String> map = new HashMap<String, String>();
				map.put("name1", "value1");
				map.put("name2", "value2");
				return map;
			}
		};
		// 15秒超时，计算方法为5+(5+5*1)：重试1次，重试时上次时间+上次时间*扩时参数
		stringRequest.setRetryPolicy((new DefaultRetryPolicy(5000, 1, 1f)));
		HttpHandler.add(stringRequest);
	}

	private void testHttpJsonHandler() {
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://m.weather.com.cn/atad/101010100.html",
				null, new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						txt_json.setText(response.toString());
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						txt_json.setText(error.getMessage());
					}
				});
		HttpHandler.add(jsonObjectRequest);// HttpHandler已经在BapApplication中进行过初始化，所以可以直接使用
	}
}