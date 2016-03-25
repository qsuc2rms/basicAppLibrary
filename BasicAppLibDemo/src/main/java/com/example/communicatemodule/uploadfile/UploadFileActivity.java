package com.example.communicatemodule.uploadfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.crypto.spec.OAEPParameterSpec;

import com.example.basicapplibdemo.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.provider.MediaStore.Files;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.baplib.common.FileSizeUtil;
import com.baplib.util.file.FileChooser;

public class UploadFileActivity extends Activity {

	private ImageView img_File;
	private TextView txt_FileName;
	private TextView txt_FileName2;
	private TextView txt_FileName3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_uploadfile);

		Button btn_FirstEvent = (Button) findViewById(R.id.btn_choosefile);
		Button btn_Upload = (Button) findViewById(R.id.btn_uploadfile);
		img_File = (ImageView) findViewById(R.id.img_uploadfile);
		txt_FileName = (TextView) findViewById(R.id.txt_filename);
		txt_FileName2 = (TextView) findViewById(R.id.txt_filename2);
		txt_FileName3 = (TextView) findViewById(R.id.txt_filename3);

		btn_FirstEvent.setOnClickListener(new View.OnClickListener() {// 原始用法
					@Override
					public void onClick(View v) {
						showChooser();
					}
				});
		btn_Upload.setOnClickListener(new View.OnClickListener() {// 上传文件
					@Override
					public void onClick(View v) {
						if (!path.isEmpty())
							doUpload();
						if (!path2.isEmpty())
							doUpload2();
						if (!path3.isEmpty())
							doUpload3();
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

	private String path = "";
	private String path2 = "";
	private String path3 = "";

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (path.isEmpty()) {
			path = FileChooser.getFilePath(requestCode, resultCode, data, this);
//			double size=FileSizeUtil.getFileOrFilesSize(path, FileSizeUtil.SIZETYPE_B);
//			long lsize=Long.valueOf(size);
//			txt_FileName.setText("File Name: " + path+" "+lsize);
			txt_FileName.setText("File Name: " + path+" "+FileSizeUtil.getAutoFileOrFilesSize(path));//.getFileOrFilesSize(path, FileSizeUtil.SIZETYPE_MB));
//			Bitmap bmp = decodeSampledBitmapFromResource(path, 200, 200);// 压缩显示，避免显示空白，节省内存
//			img_File.setImageBitmap(bmp);
			
			ImageLoader.getInstance().displayImage("file://"+path,img_File);
			
		} else if (path2.isEmpty()) {
			path2 = FileChooser.getFilePath(requestCode, resultCode, data, this);
			txt_FileName2.setText("File Name: " + path2+" "+FileSizeUtil.getAutoFileOrFilesSize(path2));//.getFileOrFilesSize(path2, FileSizeUtil.SIZETYPE_MB));
			Bitmap bmp = decodeSampledBitmapFromResource(path2, 200, 200);// 压缩显示，避免显示空白，节省内存
			img_File.setImageBitmap(bmp);
		} else {
			path3 = FileChooser.getFilePath(requestCode, resultCode, data, this);
			txt_FileName3.setText("File Name: " + path3+" "+FileSizeUtil.getAutoFileOrFilesSize(path3));//.getFileOrFilesSize(path3, FileSizeUtil.SIZETYPE_MB));
			Bitmap bmp = decodeSampledBitmapFromResource(path3, 200, 200);// 压缩显示，避免显示空白，节省内存
			img_File.setImageBitmap(bmp);
		}

	}

	/*
	 * 文件过大需要压缩的
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		// 源图片的高度和宽度
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (height > reqHeight || width > reqWidth) {
			// 计算出实际宽高和目标宽高的比率
			final int heightRatio = Math.round((float) height / (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			// 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
			// 一定都会大于等于目标的宽和高。
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

	/*
	 * 文件过大需要压缩的
	 */
	public static Bitmap decodeSampledBitmapFromResource(String pathName, int reqWidth, int reqHeight) {
		// 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(pathName, options);// .decodeResource(res,
													// resId, options);
		// 调用上面定义的方法计算inSampleSize值
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
		// 使用获取到的inSampleSize值再次解析图片
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(pathName, options);
	}

	/**
	 * 加载本地图片
	 * 
	 * @param url
	 * @return
	 */
	public static Bitmap getLoacalBitmap(String url) {
		try {
			FileInputStream fis = new FileInputStream(url);
			return BitmapFactory.decodeStream(fis); // /把流转化为Bitmap图片

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Log.e("err", e.getMessage());
			return null;
		}
	}

	private void doUpload() {
		String filePath = path;
		String uploadHost = "http://192.168.0.102:8080/FileUploadServlet";
		
		double fileSize= FileSizeUtil.getFileOrFilesSize(filePath, FileSizeUtil.SIZETYPE_B);
		
		RequestParams params = new RequestParams();
		params.addHeader("filesize",fileSize+"");
		params.addBodyParameter(filePath.replace("/", ""), new File(filePath));
		uploadMethod(params, uploadHost);
	}

	public void uploadMethod(final RequestParams params, final String uploadHost) {
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, uploadHost, params, new RequestCallBack<String>() {
			@Override
			public void onStart() {
				txt_FileName.setText("conn...");
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {
				if (isUploading) {
					txt_FileName.setText("upload: " + current + "/" + total);
				} else {
					txt_FileName.setText("reply: " + current + "/" + total);
				}
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				txt_FileName.setText("reply: " + responseInfo.result);
				Log.e("testdemo", responseInfo.result);
				path="";
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				txt_FileName.setText("fail: " +error.getExceptionCode() + ":" + msg);
			}
		});
		
	}

	private void doUpload2() {
		String filePath = path2;
		String uploadHost = "http://192.168.0.102:8080/FileUploadServlet";
		RequestParams params = new RequestParams();
		params.addBodyParameter("msg", "testfile");
		params.addBodyParameter(filePath.replace("/", ""), new File(filePath));
		uploadMethod2(params, uploadHost);
	}

	public void uploadMethod2(final RequestParams params, final String uploadHost) {
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, uploadHost, params, new RequestCallBack<String>() {
			@Override
			public void onStart() {
				txt_FileName2.setText("conn...");
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {
				if (isUploading) {
					txt_FileName2.setText("upload: " + current + "/" + total);
				} else {
					txt_FileName2.setText("reply: " + current + "/" + total);
				}
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				txt_FileName2.setText("reply: " + responseInfo.result);
				path2="";
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				txt_FileName2.setText(error.getExceptionCode() + ":" + msg);
			}
		});
	}

	private void doUpload3() {
		String filePath = path3;
		String uploadHost = "http://192.168.0.102:8080/FileUploadServlet";
		RequestParams params = new RequestParams();
		params.addBodyParameter("msg", "testfile");
		params.addBodyParameter(filePath.replace("/", ""), new File(filePath));
		uploadMethod3(params, uploadHost);
	}

	public void uploadMethod3(final RequestParams params, final String uploadHost) {
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, uploadHost, params, new RequestCallBack<String>() {
			@Override
			public void onStart() {
				txt_FileName3.setText("conn...");
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {
				if (isUploading) {
					txt_FileName3.setText("upload: " + current + "/" + total);
				} else {
					txt_FileName3.setText("reply: " + current + "/" + total);
				}
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				txt_FileName3.setText("reply: " + responseInfo.result);
				path3="";
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				txt_FileName3.setText(error.getExceptionCode() + ":" + msg);
			}
		});
	}
}
