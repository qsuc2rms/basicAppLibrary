package com.example.uimodule.progressbar;

import java.util.ArrayList;
import java.util.List;

import com.baplib.common.FileSizeUtil;
import com.baplib.net.download.BapImageLoader;
import com.baplib.ui.view.CommonProgressDialog;
import com.baplib.util.file.FileChooser;
import com.example.basicapplibdemo.R;
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
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class CircleProgressbarTitleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);//必须首先设置进度条
		setContentView(R.layout.activity_progressbarcircletitle);
		setProgressBarIndeterminateVisibility(true);

		final Button btn_showprogresscircle = (Button) findViewById(R.id.btn_showprogresscircle);

		btn_showprogresscircle.setOnClickListener(new View.OnClickListener() {// 选择本地图片
					@Override
					public void onClick(View v) {
						if (!btn_showprogresscircle.getText().equals("隐藏进度条")) {
							btn_showprogresscircle.setText("隐藏进度条");
							setProgressBarIndeterminateVisibility(true);
						} else {
							btn_showprogresscircle.setText("显示进度条");
							setProgressBarIndeterminateVisibility(false);
						}

					}
				});

	}

}
