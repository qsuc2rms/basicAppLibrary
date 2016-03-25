package com.baplib.db;

import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


public abstract class BapDB extends OrmLiteSqliteOpenHelper {

	public BapDB(Context context, String databaseName, CursorFactory factory, int databaseVersion) {
		super(context, databaseName, factory, databaseVersion);
	}

	public BapDB(Context context, String databaseName, CursorFactory factory, int databaseVersion,
			int configFileId) {
		super(context, databaseName, factory, databaseVersion, configFileId);
	}

	public BapDB(Context context, String databaseName, CursorFactory factory, int databaseVersion, File configFile) {
		super(context, databaseName, factory, databaseVersion, configFile);
	}

	public BapDB(Context arg0, String arg1, CursorFactory arg2, int arg3, InputStream arg4) {
		super(arg0, arg1, arg2, arg3, arg4);
	}

	/*
	 * 创建数据库
	 */
	public abstract void onCreate(SQLiteDatabase paramSQLiteDatabase, ConnectionSource paramConnectionSource);

	/*
	 * 更新数据库
	 */
	public abstract void onUpgrade(SQLiteDatabase paramSQLiteDatabase, ConnectionSource paramConnectionSource,
			int oldVersionNo, int newVersionNo);

}
