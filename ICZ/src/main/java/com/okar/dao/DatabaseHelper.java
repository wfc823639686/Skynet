package com.okar.dao;

import com.j256.ormlite.android.DatabaseTableConfigUtil;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.j256.ormlite.table.TableUtils;
import com.okar.model.Commodity;
import com.okar.entry.MsgBody;
import com.okar.entry.TextMsg;
import com.okar.service.MsgParser;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    // 数据库名称
    private static final String DATABASE_NAME = "iczcache1.db";
    // 数据库version
    private static final int DATABASE_VERSION = 7;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // 可以用配置文件来生成 数据表，有点繁琐，不喜欢用
        // super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            //建立Commodity表
//            DatabaseTableConfig<TextMsg> config = DatabaseTableConfigUtil.fromClass(connectionSource, TextMsg.class);
//            TableUtils.createTableIfNotExists(connectionSource,config);
            Log.e(TAG, "onCreate tables -----> ");
            TableUtils.createTable(connectionSource, TextMsg.class);
            TableUtils.createTable(connectionSource, MsgBody.class);
        } catch (SQLException e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.e(TAG, "onUpgrade tables -----> ");
            Log.e(TAG, "oldVersion -> "+oldVersion);
            Log.e(TAG, "newVersion -> "+newVersion);
            TableUtils.dropTable(connectionSource, TextMsg.class, true);
            TableUtils.dropTable(connectionSource, MsgBody.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }
    }

    /**
     * 释放 DAO
     */
    @Override
    public void close() {
        super.close();
    }

}