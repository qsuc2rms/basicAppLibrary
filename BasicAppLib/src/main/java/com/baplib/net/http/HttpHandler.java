package com.baplib.net.http;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class HttpHandler {

	private static RequestQueue _requestQueue;
	/*
	 * 必须在Application中先进行初始化，否则不可用
	 */
	public static void Init(Context ctx){
		_requestQueue=Volley.newRequestQueue(ctx);
	}
	
	public static void add(Request request){
		_requestQueue.add(request);
	}
	
	

}
