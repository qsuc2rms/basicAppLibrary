package com.baplib.net.download;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.ops.basicapplib.R;


public class BapImageLoader extends ImageLoader{

		private static BapImageLoader _instance;

		public static synchronized BapImageLoader getInstance() {
			if (_instance == null) {
				synchronized (BapImageLoader.class) {
					_instance = new BapImageLoader();
				}
			}
			return _instance;
		}

		@Override
		public void displayImage(String imageUrl,ImageView imageView){
			
			DisplayImageOptions options = new DisplayImageOptions.Builder()  
			 .showImageOnLoading(R.drawable.ic_imageloading) //设置图片在下载期间显示的图片  
			 .showImageForEmptyUri(R.drawable.ic_imagefail)//设置图片Uri为空或是错误的时候显示的图片  
			.showImageOnFail(R.drawable.ic_imagefail)  //设置图片加载/解码过程中错误时候显示的图片
			.cacheInMemory(true)//设置下载的图片是否缓存在内存中  
			.cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中  
			.considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
			.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示  
			.resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位  
			.displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少  
			.displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间  
			.build();//构建完成  
			
			ImageLoader.getInstance().displayImage(imageUrl, imageView,options);
		}
}
