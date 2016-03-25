package com.baplib.util.cache;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.baplib.common.AndroidVersionCheckUtils;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

/**
 * @Title ExternalOverFroyoUtils
 * @version V1.0
 */
@TargetApi(9)
public class ExternalOverFroyoUtils
{
	/**
	 * 
	 */
	public static boolean hasExternalStorage()
	{
		Boolean externalStorage = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		return externalStorage;
	}

	/**
	 * 
	 */
	public static long getUsableSpace(File path)
	{
		if (AndroidVersionCheckUtils.hasGingerbread())
		{
			return path.getUsableSpace();
		}
		final StatFs stats = new StatFs(path.getPath());
		return (long) stats.getBlockSize() * (long) stats.getAvailableBlocks();
	}

	/**
	 * 
	 */
	public static File getExternalCacheDir(Context context)
	{
		if (AndroidVersionCheckUtils.hasFroyo())
		{
			return context.getExternalCacheDir();
		}
		final String cacheDir = "/Android/data/" + context.getPackageName()
				+ "/cache/";
		return new File(Environment.getExternalStorageDirectory().getPath()
				+ cacheDir);
	}

	/**
	 * 
	 */
	public static boolean isExternalStorageRemovable()
	{
		if (AndroidVersionCheckUtils.hasGingerbread())
		{
			return Environment.isExternalStorageRemovable();
		}
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
		final String cachePath = Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState()) || !isExternalStorageRemovable() ? getExternalCacheDir(
				context).getPath()
				: context.getCacheDir().getPath();

		return new File(cachePath + File.separator + uniqueName);
	}

	/**
	 * 
	 */
	public static File getSystemDiskCacheDir(Context context)
	{
		final String cachePath = Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState()) || !isExternalStorageRemovable() ? getExternalCacheDir(
				context).getPath()
				: context.getCacheDir().getPath();

		return new File(cachePath);
	}

	public static int getMemoryClass(Context context)
	{
		return ((ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
	}

}
