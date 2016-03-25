package com.baplib.log;

import java.io.File;
import java.io.FileInputStream;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import android.content.Context;
import android.os.Environment;
import android.provider.Settings.Global;
import android.util.Log;
import de.mindpipe.android.logging.log4j.LogConfigurator;

/**
 * 日记组建工具类
 * 需要在每个实用类中新建一个实例才能使用
 * 使用getBapLogger方法新建实例
 */
public class BapLogger{
	
	private Logger gLogger;

	private BapLogger(Logger logger) {
		gLogger=logger;
		init();
		// TODO Auto-generated constructor stub
	}
	
	public static BapLogger getBapLogger(String name) {
		Logger l=LogManager.getLogger(name);
		return new BapLogger(l);
	}

	private final String DEFAULT_STORAGE = Environment.getExternalStorageDirectory() + File.separator;// 日志文件了路径
	private final long DEFAULT_FILESIZEUNITE = 1024 * 1024;// 日志文件夹长度单位
	private LogConfigurator logConfigurator;
	private boolean isInit=false;
	
	/**
	 * 采用默认值初始化,初始化TAG、日志、日志文件最大长度
	 */
	public void init() {
		logConfigurator = new LogConfigurator();
		logConfigurator.setFilePattern("%d - [%p::%c] - %m%n");
		logConfigurator.setFileName(DEFAULT_STORAGE + BapLogConfig.getInstance().getLogFile());
		logConfigurator.setRootLevel(BapLogConfig.getInstance().getLogRootLevel());// Set the root log level
		logConfigurator.setLevel("org.apache", BapLogConfig.getInstance().getLogRootLevel());
		logConfigurator.setMaxFileSize(DEFAULT_FILESIZEUNITE *BapLogConfig.getInstance().getLogSize() / 6); // 设置文件夹最大长度，内部可以放置6个日志文件
		logConfigurator.configure();
		isInit=true;
	}

	/**
	 * 初始化,初始化TAG、日志、日志文件最大长度
	 * 
	 * @param path
	 *            路径
	 * @param tag
	 *            日志tag
	 * @param maxSize
	 *            日志文件最大长度
	 */
	public void init(String filename, String tag, Level rootlevel, int maxSize) {

		logConfigurator = new LogConfigurator();
		logConfigurator.setFilePattern("%d - [%p::%c] - %m%n");
		logConfigurator.setFileName(DEFAULT_STORAGE + filename);
		logConfigurator.setRootLevel(rootlevel);// Set the root log level
		logConfigurator.setLevel("org.apache", rootlevel);
		logConfigurator.setMaxFileSize(DEFAULT_FILESIZEUNITE * maxSize / 6); // 设置文件夹最大长度，内部可以放置6个日志文件
		logConfigurator.configure();
		isInit=true;
	}

	/**
	 * warn级日志
	 * 
	 * @param log
	 *            日志内容
	 */
	public void warn(String log) {
		if (!isInit) {
			init();
		}
		gLogger.warn(log);
	}

	/**
	 * error级日志
	 * 
	 * @param log
	 *            日志内容
	 */
	public void error(String log) {
		if (!isInit) {
			init();
		}
		gLogger.error(log);
	}

	/**
	 * debug级日志
	 * 
	 * @param log
	 *            日志内容
	 */
	public void debug(String log) {
		if (!isInit) {
			init();
		}
		gLogger.debug(log);
	}

	/**
	 * info级日志
	 * 
	 * @param log
	 *            日志内容
	 */
	public void info(String log) {
		if (!isInit) {
			init();
		}
		gLogger.info(log);
	}

	/*******************************************************************************************/

	/**
	 * 调用此方法自动计算指定文件或指定文件夹的大小
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 计算好的带B、KB、MB、GB的字符串
	 */
	public long getAutoFileOrFilesSize(File file) {
		// File file=new File(filePath);
		long blockSize = 0;
		try {
			if (file.isDirectory()) {
				blockSize = getFileSizes(file);
			} else {
				blockSize = getFileSize(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return blockSize;
	}

	/**
	 * 获取指定文件夹
	 * 
	 * @param f
	 * @return
	 * @throws Exception
	 */
	private long getFileSizes(File f) throws Exception {
		long size = 0;
		File flist[] = f.listFiles();
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getFileSizes(flist[i]);
			} else {
				size = size + getFileSize(flist[i]);
			}
		}
		return size;
	}

	/**
	 * 获取指定文件大小
	 * 
	 * @param f
	 * @return
	 * @throws Exception
	 */
	private long getFileSize(File file) throws Exception {
		long size = 0;
		if (file.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(file);
			size = fis.available();
		} else {
		}
		return size;
	}

	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * 
	 * @param dir
	 *            将要删除的文件目录
	 * @return boolean Returns "true" if all deletions were successful. If a
	 *         deletion fails, the method stops attempting to delete and returns
	 *         "false".
	 */
	private boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			// 递归删除目录中的子目录下
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}
}
