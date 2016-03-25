package com.baplib.util.cache;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.StatFs;

/**
 * @Title ExternalUnderFroyoUtils
 * @version V1.0
 */
public class ExternalUnderFroyoUtils
{
	/**
	 * 检查外存储器
	 */
	public static boolean hasExternalStorage()
	{
		Boolean externalStorage = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		return externalStorage;
	}

	/**
	 * 获取存储空间
	 */
	public static long getUsableSpace(File path)
	{
		final StatFs stats = new StatFs(path.getPath());
		return (long) stats.getBlockSize() * (long) stats.getAvailableBlocks();
	}

	/**
	 * 获取存储路径
	 */
	public static File getExternalCacheDir(Context context)
	{
		final String cacheDir = "/Android/data/" + context.getPackageName()
				+ "/cache/";
		return new File(Environment.getExternalStorageDirectory().getPath()
				+ cacheDir);
	}

	/**
	 * 外存储器是否可拆卸
	 */
	public static boolean isExternalStorageRemovable()
	{
		return true;
	}

	/**
	 * 
	 */
	public static String hashKeyForDisk(String key)
	{
		String cacheKey;
		try
		{
			final MessageDigest mDigest = MessageDigest.getInstance("MD5");
			mDigest.update(key.getBytes());
			cacheKey = bytesToHexString(mDigest.digest());
		} catch (NoSuchAlgorithmException e)
		{
			cacheKey = String.valueOf(key.hashCode());
		}
		return cacheKey;
	}

	private static String bytesToHexString(byte[] bytes)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++)
		{
			String hex = Integer.toHexString(0xFF & bytes[i]);
			if (hex.length() == 1)
			{
				sb.append('0');
			}
			sb.append(hex);
		}
		return sb.toString();
	}

	/**
	 * 
	 */
	public static File getDiskCacheDir(Context context, String uniqueName)
	{
		// ����Ƿ�װ��洢ý�������õ�,���������,����ʹ��
		// �ⲿ���� Ŀ¼
		// ����ʹ���ڲ����� Ŀ¼
		final String cachePath = Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState()) ? getExternalCacheDir(context)
				.getPath() : context.getCacheDir().getPath();
		return new File(cachePath + File.separator + uniqueName);
	}

	/**
	 * 
	 */
	public static File getSystemDiskCacheDir(Context context)
	{
		final String cachePath = Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState()) ? getExternalCacheDir(context)
				.getPath() : context.getCacheDir().getPath();
		return new File(cachePath);
	}

	/**
	 * 
	 */
	public static int getBitmapSize(Bitmap bitmap)
	{
		return bitmap.getRowBytes() * bitmap.getHeight();
	}

	public static int getMemoryClass(Context context)
	{
		return 1024 * 1024 * 5; // 5MB;
	}

}
