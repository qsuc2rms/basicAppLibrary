package com.example.db;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DemoDB extends com.baplib.db.BapDB {

	private static DemoDB instance;//实体
	private static final String DATABASE_NAME = "DemoDB.db";//数据库名称
	private static final int DATABASE_VERSION = 1;//数据库版本号，升级数据库时，更新该版本号，就能自动运行onupgrade函数
	private Map<String, Dao> daos = new HashMap<String, Dao>();//每个数据表对应一个数据操作实体

	/*
	 * 构造函数初始化
	 */
	private DemoDB(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/*
	 * 获取一个线程安全的实例
	 */
	public static synchronized DemoDB getInstance(Context context) {
		context = context.getApplicationContext();
		if (instance == null) {
			synchronized (DemoDB.class) {
				if (instance == null) {
					instance = new DemoDB(context);
				}
			}
		}
		return instance;
	}

	/*
	 * 根据数据表的实体类，获取一个数据表操作对象
	 */
	public synchronized Dao getDao(Class clazz) throws SQLException {
		Dao dao = null;
		String classname = clazz.getSimpleName();

		if (daos.containsKey(classname)) {
			dao = daos.get(classname);
		}
		if (dao == null) {
			dao = super.getDao(clazz);
			daos.put(classname, dao);
		}
		return dao;
	}

	/*
	 * 初次建表
	 */
	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
		// try {
		// TableUtils.createTable(connectionSource, ActivityItem.class);
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }

	}

	/*
	 * 后续升级时，可参照实例，明确历次数据库升级的操作
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource arg1, int arg2, int arg3) {
		// try {
		// int upgradeVersion=arg2;
		// //v16->v17
		// if(upgradeVersion==16){
		// TableUtils.createTableIfNotExists(connectionSource,
		// ResourceInfos.class);
		// db.execSQL("ALTER TABLE ActivityItem Add COLUMN CITY String DEFAULT null");
		// db.execSQL("UPDATE ActivityItem SET CITY ='北京' WHERE CITY is null ");
		// upgradeVersion=17;
		// }
		//
		// //后续版本升级
		// //。。。
		// //最终判断
		// if(upgradeVersion!=arg3){
		// Log.e(DATABASE_NAME, "instead");
		// TableUtils.dropTable(connectionSource, ActivityItem.class, true);
		// onCreate(db, connectionSource);
		// }
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
	}

	/*
	 * 退出时释放所有的额数据操作对象
	 */
	public void close() {
		super.close();
		for (String key : daos.keySet()) {
			Dao dao = daos.get(key);
			dao = null;
		}
	}

}
