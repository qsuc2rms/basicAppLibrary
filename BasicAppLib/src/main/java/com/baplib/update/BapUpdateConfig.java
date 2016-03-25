package com.baplib.update;

import org.apache.log4j.Level;

import com.baplib.common.DefaultParams;

public class BapUpdateConfig {

	private static BapUpdateConfig _instance;

	public static synchronized BapUpdateConfig getInstance() {
		if (_instance == null) {
			synchronized (BapUpdateConfig.class) {
				_instance = new BapUpdateConfig();
			}
		}
		return _instance;
	}

	private BapUpdateConfig() {
		// TODO Auto-generated constructor stub
	}

	// 日志标签，一般填写记录日志的类名
	private String _apkName = DefaultParams.DEFAULT_UPDATEAPK;

	public String getApkName() {
		return _apkName;
	}

	public void setApkName(String apkName) {
		_apkName = apkName;
	}

	// 日志文件名称，一般填写“工程文件夹/工程_log”
	private String _savePath = DefaultParams.DEFAULT_UPDATEFILEPATH;

	public String getSavePath() {
		return _savePath;
	}

	public void setSavePath(String savePath) {
		_savePath = savePath;
	}
	
	// 日志起始记录等级，Level格式 ，一般选择Level.ERROR
	private String _url = DefaultParams.DEFAULT_UPDATEURL;

	public String getURL() {
		return _url;
	}

	public void setURL(String url) {
		_url = url;
	}
	
	// 日志文件最大长度，单位是1024*1024
	private String _version = "1";

	public String getLatestVersion() {
		return _version;
	}

	public void setLatestVersion(String version) {
		_version = version;
	}

}
