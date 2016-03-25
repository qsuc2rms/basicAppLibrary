package com.baplib.ui.view;

import com.ops.basicapplib.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

public class CommonProgressDialog {

	private static CommonProgressDialog instance;
	private static Context mcontext;
	private ProgressDialog mProDialog;

	public CommonProgressDialog(Context ctx) {
		mcontext = ctx;
		mProDialog = new ProgressDialog(mcontext);
	}

	public static synchronized CommonProgressDialog getInstance(Context context) {
		if (instance == null) {
			mcontext = context.getApplicationContext();
			synchronized (CommonProgressDialog.class) {
				if (instance == null) {
					instance = new CommonProgressDialog(context);
				}
			}
		}
		return instance;
	}
	
	public static void clear(){
		instance=null;
	}

	public void showProgressDialog(String tip) {
		if (mProDialog == null)
			return;

		mProDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProDialog.setMessage(tip);
		mProDialog.setIndeterminate(false);
		mProDialog.setCancelable(false);
		mProDialog.show();
		asynCheckTimeout();
	}

	public void hideProgressDialog() {
		if (mProDialog != null) {
			mProDialog.hide();
			cancelCheckTimeout();
		}
	}

	Handler checkTimeoutHandler = new Handler();
	Runnable checkTimeoutRunnable = new Runnable() {
		@Override
		public void run() {
			hideProgressDialog();
			ShowToast(CommonProgreeDialogConfig.getInstance(mcontext).getORTTips());
		}
	};

	private void asynCheckTimeout() {
		if (CommonProgreeDialogConfig.getInstance(mcontext).getDoORT())// 如果启动了进度条超时，则开始计时，默认不启动
			checkTimeoutHandler.postDelayed(checkTimeoutRunnable, CommonProgreeDialogConfig.getInstance(mcontext)
					.getORTSeconds());// 默认30秒后提示已超时
	}

	private void cancelCheckTimeout() {
		checkTimeoutHandler.removeCallbacks(checkTimeoutRunnable);
	}

	private void ShowToast(String msg) {
		Toast.makeText(mcontext, msg, Toast.LENGTH_SHORT).show();
	}
}
