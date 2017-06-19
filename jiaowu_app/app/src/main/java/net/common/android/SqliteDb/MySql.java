package net.common.android.SqliteDb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 创建签到，习题，反馈，通知的数据库类
 * Created by Administrator on 2017/6/13.
 */

public class MySql extends SQLiteOpenHelper {
	public MySql(Context context) {
		super(context, "Mys_sql.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table signIn(_id Integer primary key autoincrement,name varchar(30)," +
				"grade varchar(20),number varchar(20),course varchar(20))");
		db.execSQL("create table exercises(_id Integer primary key autoincrement,name varchar(30)," +
				"title varchar(60),content varchar(500),time varchar(25))");
		db.execSQL("create table notice(_id Integer primary key autoincrement,name varchar(30)," +
				"title varchar(60),content varchar(600),grade varchar(20),time varchar(25),status varchar(10))");
		db.execSQL("create table feedback(_id Integer primary key autoincrement,name varchar(30)," +
				"title varchar(60),content varchar(600),time varchar(25))");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
