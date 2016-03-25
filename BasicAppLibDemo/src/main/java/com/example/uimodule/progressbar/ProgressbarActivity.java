package com.example.uimodule.progressbar;

import java.util.ArrayList;
import java.util.List;

import com.baplib.common.FileSizeUtil;
import com.baplib.net.download.BapImageLoader;
import com.baplib.ui.view.CommonProgressDialog;
import com.baplib.util.file.FileChooser;
import com.example.basicapplibdemo.R;
import com.example.basicmodule.multilanguage.ShowMultiLanguageActivity;
import com.example.communicatemodule.eventbus.FirstEvent;
import com.example.communicatemodule.eventbus.SecondEvent;
import com.example.communicatemodule.eventbus.ThirdEvent;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import de.greenrobot.event.EventBus;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ProgressbarActivity extends Activity {
	private ProgressBar bar_progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progressbar);

		bar_progress = (ProgressBar) findViewById(R.id.progressbar_updown);

		Button btn_popcircle = (Button) findViewById(R.id.btn_pupprogresscircle);
		Button btn_popcircle2 = (Button) findViewById(R.id.btn_pupprogresscircle2);
		Button btn_pophorizontal = (Button) findViewById(R.id.btn_pupprogresshorizontal);

		Button btn_progressgo = (Button) findViewById(R.id.btn_progressgo);
		Button btn_progresswindowcircle = (Button) findViewById(R.id.btn_progresswindowcircle);
		Button btn_progresswindowhorizontal = (Button) findViewById(R.id.btn_progresswindowhorizontal);

		btn_popcircle.setOnClickListener(new View.OnClickListener() {// 选择本地图片
					@Override
					public void onClick(View v) {
						showProgressCircle();
					}
				});
		btn_popcircle2.setOnClickListener(new View.OnClickListener() {// 选择本地图片
					@Override
					public void onClick(View v) {
						showProgressCircle2();
					}
				});
		btn_pophorizontal.setOnClickListener(new View.OnClickListener() {// 选择本地图片
			@Override
			public void onClick(View v) {
				showProgressHorizontal();
			}
		});

		btn_progressgo.setOnClickListener(new View.OnClickListener() {// 选择本地图片
					@Override
					public void onClick(View v) {
						bar_progress.setProgress(0);
						avtivateProgressbar();
					}
				});
		btn_progresswindowcircle.setOnClickListener(new View.OnClickListener() {// 选择本地图片
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(getApplicationContext(), CircleProgressbarTitleActivity.class);
						startActivity(intent);
					}
				});
		btn_progresswindowhorizontal.setOnClickListener(new View.OnClickListener() {// 选择本地图片
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(getApplicationContext(), HorizontalProgressbarTitleActivity.class);
						startActivity(intent);
					}
				});
	}

	private void showProgressCircle() {
		CommonProgressDialog.getInstance(this).showProgressDialog("Please Wait...");
		Handler mHandler = new Handler();
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				CommonProgressDialog.getInstance(ProgressbarActivity.this).hideProgressDialog();
			}
		}, 3000);
	}

	private void showProgressCircle2() {
		ProgressDialog mypDialog = new ProgressDialog(this);
		// 实例化
		mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		// 设置进度条风格，风格为圆形，旋转的
		mypDialog.setTitle("BapLibDemo");
		// 设置ProgressDialog 标题
		mypDialog.setMessage("请等待，按返回键可退出");
		// 设置ProgressDialog 提示信息
		mypDialog.setIcon(R.drawable.ic_launcher);
		// 设置ProgressDialog 标题图标
//		mypDialog.setButton(
		// 设置ProgressDialog 的一个Button
		mypDialog.setIndeterminate(false);
		// 设置ProgressDialog 的进度条是否不明确
		mypDialog.setCancelable(true);
		// 设置ProgressDialog 是否可以按退回按键取消
		mypDialog.show();
		// 让ProgressDialog显示
	}
	
	private void showProgressHorizontal() {
		ProgressDialog mypDialog=new ProgressDialog(this);
	    //实例化
	   mypDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	  //设置进度条风格，风格为长形，有刻度的
	  mypDialog.setTitle("BapLibDemo");
	  //设置ProgressDialog 标题
	  mypDialog.setMessage("请等待，按返回键可退出");
	  //设置ProgressDialog 提示信息
	  mypDialog.setIcon(R.drawable.ic_launcher);
	  //设置ProgressDialog 标题图标
	  mypDialog.setProgress(59);
	  //设置ProgressDialog 进度条进度
	  mypDialog.setButton("option1",new DialogInterface.OnClickListener() {		
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(ProgressbarActivity.this, "option1 pressed", Toast.LENGTH_SHORT).show();
			}
		});
	  mypDialog.setButton2("option2",new DialogInterface.OnClickListener() {		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			Toast.makeText(ProgressbarActivity.this, "option2 pressed", Toast.LENGTH_SHORT).show();
		}
	});
	  //设置ProgressDialog 的一个Button
	  mypDialog.setIndeterminate(false);
	  //设置ProgressDialog 的进度条是否不明确
	  mypDialog.setCancelable(true);
	  //设置ProgressDialog 是否可以按退回按键取消
	  mypDialog.show();
	  //让ProgressDialog显示   
	  
	  avtivateHorizontalProgressbar(mypDialog);
	}
	
	private void avtivateHorizontalProgressbar(final ProgressDialog mypDialog) {
		Handler mHandler = new Handler();
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				mypDialog.incrementProgressBy(1);
				mypDialog.incrementSecondaryProgressBy(2);
				// //ProgressBar背后的第二个进度条 进度值增加5
				// bar_progress.incrementSecondaryProgressBy(-5);
				// ProgressBar背后的第二个进度条 进度值减少5

				if (mypDialog.getProgress() < 100) {
					avtivateHorizontalProgressbar(mypDialog);
				}else{
					mypDialog.setProgress(0);
					avtivateHorizontalProgressbar(mypDialog);
				}
			}
		}, 100);
	}

	private void avtivateProgressbar() {
		Handler mHandler = new Handler();
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {

				bar_progress.incrementProgressBy(5);
				// //ProgressBar进度值增加5
				// bar_progress.incrementProgressBy(-5);
				// ProgressBar进度值减少5
				bar_progress.incrementSecondaryProgressBy(5);
				// //ProgressBar背后的第二个进度条 进度值增加5
				// bar_progress.incrementSecondaryProgressBy(-5);
				// ProgressBar背后的第二个进度条 进度值减少5

				if (bar_progress.getProgress() < 100) {
					avtivateProgressbar();
				}
			}
		}, 100);
	}

}
