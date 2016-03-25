package com.baplib.ui.view;

import com.ops.basicapplib.R;

import android.content.Context;

public class CommonProgreeDialogConfig {

	private static CommonProgreeDialogConfig _instance;
	private Context _context;

	public static synchronized CommonProgreeDialogConfig getInstance(Context context) {
		if (_instance == null) {
			synchronized (CommonProgreeDialogConfig.class) {
				_instance = new CommonProgreeDialogConfig(context);
			}
		}
		return _instance;
	}

	private CommonProgreeDialogConfig(Context context) {
		_context = context;
	}

	// 进度条是否设置默认超时
	private boolean _doORT = false;

	public boolean getDoORT() {
		return _doORT;
	}

	public void setDoORT(boolean doORT) {
		_doORT = doORT;
	}

	// 进度条默认超时的文字提示
	private String _tips;

	public String getORTTips() {
		if (_tips == null || _tips.isEmpty()) {
			_tips = _context.getResources().getString(R.string.outoftime);
		}
		return _tips;
	}

	public void setTips(String tips) {
		_tips = tips;
	}

	// 进度条默认超时时间(秒)
	private long _delayseconds = 30;

	public long getORTSeconds() {
		return _delayseconds;
	}

	public void setORTSeconds(long seconds) {
		_delayseconds = seconds;
	}
}
