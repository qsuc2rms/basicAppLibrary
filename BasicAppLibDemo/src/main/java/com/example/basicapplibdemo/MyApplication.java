package com.example.basicapplibdemo;

import com.baplib.BapApplication;
import com.baplib.log.BapLogConfig;

public class MyApplication extends BapApplication{

	public MyApplication() {
		Init();//在此配置各类通用参数
		
	}
	
	private void Init(){
		//先统一设置日志文件夹，如不设置，则采用默认地址“BapLib/Bap_log”
		BapLogConfig.getInstance().setLogFile("BapDemo/bap_log");
		
	}

	
}
