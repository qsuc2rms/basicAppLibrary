package com.example.communicatemodule.eventbus;

public class SecondEvent{

	private String mMsg;
	public SecondEvent(String msg) {
		// TODO Auto-generated constructor stub
		mMsg = "MainEvent:"+msg;
	}
	public String getMsg(){
		return mMsg;
	}
}
