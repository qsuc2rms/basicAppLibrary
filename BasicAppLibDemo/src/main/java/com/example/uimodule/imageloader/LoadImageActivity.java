package com.example.uimodule.imageloader;

import java.util.ArrayList;
import java.util.List;

import com.baplib.common.FileSizeUtil;
import com.baplib.net.download.BapImageLoader;
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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class LoadImageActivity extends Activity {
	private Button btn_FirstEvent, btn_SecondEvent, btn_ThirdEvent, btn_FourthEvent, btn_FifthEvent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loadimage);
		btn_FirstEvent = (Button) findViewById(R.id.btn_imageloader_first_event);
		btn_SecondEvent = (Button) findViewById(R.id.btn_imageloader_second_event);
		btn_ThirdEvent = (Button) findViewById(R.id.btn_imageloader_third_event);
		btn_FourthEvent = (Button) findViewById(R.id.btn_imageloader_fourth_event);
		btn_FifthEvent = (Button) findViewById(R.id.btn_imageloader_fifth_event);

		btn_FirstEvent.setOnClickListener(new View.OnClickListener() {// 选择本地图片
					@Override
					public void onClick(View v) {
						showChooser();
					}
				});

		btn_SecondEvent.setOnClickListener(new View.OnClickListener() {//加载网络图片
					@Override
					public void onClick(View v) {
						String imageUrl = "http://www.5068.com/u/faceimg/20140725173411.jpg";
						ImageView imageView = (ImageView) findViewById(R.id.img_2);
						BapImageLoader.getInstance().displayImage(imageUrl, imageView);
					}
				});

		btn_ThirdEvent.setOnClickListener(new View.OnClickListener() {// 设置加载参数、监听加载状态

					@Override
					public void onClick(View v) {
						String imageUrl = "http://pic2.nipic.com/20090331/1875303_103609042_2.jpg";
						ImageView imageView = (ImageView) findViewById(R.id.img_3);

						DisplayImageOptions options = new DisplayImageOptions.Builder()
								.showImageOnLoading(R.drawable.ic_launcher) // 设置图片在下载期间显示的图片
								.showImageForEmptyUri(R.drawable.ic_launcher)// 设置图片Uri为空或是错误的时候显示的图片
								.showImageOnFail(R.drawable.ic_launcher) // 设置图片加载/解码过程中错误时候显示的图片
								.cacheInMemory(true)// 设置下载的图片是否缓存在内存中
								.cacheOnDisc(true)// 设置下载的图片是否缓存在SD卡中
								.considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
								.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 设置图片以如何的编码方式显示
								.resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
								.displayer(new RoundedBitmapDisplayer(20))// 是否设置为圆角，弧度为多少
								.displayer(new FadeInBitmapDisplayer(100))// 是否图片加载好后渐入的动画时间
								.build();// 构建完成

						ImageLoader.getInstance().displayImage(imageUrl, imageView, options,
								new ImageLoadingListener() {

									@Override
									public void onLoadingCancelled(String arg0, View arg1) {
										// TODO Auto-generated method stub

									}

									@Override
									public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
										// TODO Auto-generated method stub
										showToast("image load completed");
									}

									@Override
									public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
										// TODO Auto-generated method stub
										showToast("image load failed");
									}

									@Override
									public void onLoadingStarted(String arg0, View arg1) {
										// TODO Auto-generated method stub
										showToast("image load started");
									}
								});
					}
				});

		btn_FourthEvent.setOnClickListener(new View.OnClickListener() {//清除图片缓存

			@Override
			public void onClick(View v) {
				onClearCatchClick();
			}
		});

		btn_FifthEvent.setOnClickListener(new View.OnClickListener() {//加载资源图片

			@Override
			public void onClick(View v) {
				onClearDiskClick();
			}
		});
	}

	private void showChooser() {
		Intent intent = FileChooser.getChooserIntent("Please select a file");
		try {
			startActivityForResult(intent, FileChooser.FILECHOOSER_CODE);
		} catch (ActivityNotFoundException e) {
			// The reason for the existence of aFileChooser
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			String path = FileChooser.getFilePath(requestCode, resultCode, data, this);
			ImageView imageView = (ImageView) findViewById(R.id.img_1);
			ImageLoader.getInstance().displayImage("file://" + path, imageView);
	}

	public void showToast(String content) {
		Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
	}

	public void onClearCatchClick() {
		Toast.makeText(this, "清除缓存成功", Toast.LENGTH_SHORT).show();
		ImageLoader.getInstance().clearMemoryCache(); // 清除内存缓存
		ImageLoader.getInstance().clearDiskCache(); // 清除本地缓存
	}

	public void onClearDiskClick() {
		ImageView imageView = (ImageView) findViewById(R.id.img_4);
		ImageLoader.getInstance().displayImage("drawable://" + R.drawable.ic_launchernew, imageView);
	}

}
