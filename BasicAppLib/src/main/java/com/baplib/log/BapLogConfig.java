package com.baplib.log;

import org.apache.log4j.Level;

import com.baplib.common.DefaultParams;

public class BapLogConfig {

	private static BapLogConfig _instance;

	public static synchronized BapLogConfig getInstance() {
		if (_instance == null) {
			synchronized (BapLogConfig.class) {
				_instance = new BapLogConfig();
			}
		}
		return _instance;
	}

	private BapLogConfig() {
		// TODO Auto-generated constructor stub
	}

	// 日志标签，一般填写记录日志的类名
	private String _tag = DefaultParams.DEFAULT_LOGTAG;

	public String getTag() {
		return _tag;
	}

	public void setTag(String tag) {
		_tag = tag;
	}

	// 日志文件名称，一般填写“工程文件夹/工程_log”
	private String _logfile = DefaultParams.DEFAULT_LOGPATH;

	public String getLogFile() {
		return _logfile;
	}

	public void setLogFile(String logfile) {
		_logfile = logfile;
	}
	
	// 日志起始记录等级，Level格式 ，一般选择Level.ERROR
	private Level _logrootlevel = Level.ERROR;

	public Level getLogRootLevel() {
		return _logrootlevel;
	}

	public void setLogRootLevel(Level logrootlevel) {
		_logrootlevel = logrootlevel;
	}
	
	// 日志文件最大长度，单位是1024*1024
	private int _logsize = 1;

	public int getLogSize() {
		return _logsize;
	}

	public void setLogSize(int logsize) {
		_logsize = logsize;
	}

}
