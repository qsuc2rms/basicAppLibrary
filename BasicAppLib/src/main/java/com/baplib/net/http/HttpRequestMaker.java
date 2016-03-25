package com.baplib.net.http;

import java.util.Map;

import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;

public class HttpRequestMaker {

	/*
	 * 默认不提供String的post方式request，需要重写
	 */
	public static StringRequest getStringPostRequest(String url,final Map<String, String> params,Listener<String> listener, ErrorListener errorListener){
		StringRequest stringRequest = new StringRequest(Request.Method.POST,url,listener,errorListener){
			@Override
			protected Map<String, String> getParams() {
				return params;
			}
		};
		return stringRequest;
	}

}
