package com.baplib;

import android.app.Application;
import android.graphics.Bitmap.CompressFormat;

import java.io.File;
import java.lang.Thread.UncaughtExceptionHandler;

import com.baplib.common.DefaultParams;
import com.baplib.net.http.HttpHandler;
import com.baplib.util.exception.CrashHandler;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

public class BapApplication extends Application {

	// BapApplication的实例
	private static BapApplication application;

	public static BapApplication getApplication() {
		return application;
	}

	// 全局异常捕捉
	private UncaughtExceptionHandler uncaughtExceptionHandler;

	public void setUncaughtExceptionHandler(UncaughtExceptionHandler uncaughtExceptionHandler) {
		this.uncaughtExceptionHandler = uncaughtExceptionHandler;
	}

	private UncaughtExceptionHandler getUncaughtExceptionHandler() {
		if (uncaughtExceptionHandler == null) {
			uncaughtExceptionHandler = CrashHandler.getInstance(this);
		}
		return uncaughtExceptionHandler;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		doOncreate();
	}

	/*
	 * 在启动时进行必要的设置
	 */
	private void doOncreate() {
		this.application = this;
		Thread.setDefaultUncaughtExceptionHandler(getUncaughtExceptionHandler());// 全局异常捕捉
		HttpHandler.Init(this);// HTTP通信(Volley)
		configImageLoader();
	}

	private void configImageLoader() {
		File cacheDir=StorageUtils.getOwnCacheDirectory(getApplicationContext(), DefaultParams.DEFAULT_CACHEPATH);  
		ImageLoaderConfiguration config = new ImageLoaderConfiguration  
			    .Builder(this)  
			    .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽  
			    //.discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75, null) // Can slow ImageLoader, use it carefully (Better don't use it)/设置缓存的详细信息，最好不要设置这个  
			    .threadPoolSize(3)//线程池内加载的数量  
			    .threadPriority(Thread.NORM_PRIORITY - 2)  
			    .denyCacheImageMultipleSizesInMemory()  
			    .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现  
			    .memoryCacheSize(2 * 1024 * 1024)    
			    .discCacheSize(50 * 1024 * 1024)    
			    .discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密  
			    .tasksProcessingOrder(QueueProcessingType.LIFO)  
			    .discCacheFileCount(100) //缓存的文件数量  
			    .discCache(new UnlimitedDiskCache(cacheDir))//自定义缓存路径  
			    .defaultDisplayImageOptions(DisplayImageOptions.createSimple())  
			    .imageDownloader(new BaseImageDownloader(this, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间  
			    .writeDebugLogs() // Remove for release app  
			    .build();//开始构建  
		
		ImageLoader.getInstance().init(config);
		
		DisplayImageOptions options;  
		
	}

}
