package com.doyoon.android.cookbook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.doyoon.android.cookbook.res.domain.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by DOYOON on 6/26/2017.
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {

    /* Singleton */
    private static DBHelper instance;
    /* Const */
    private static final String TAG = DBHelper.class.getName();
    private static final String DATABASE_NAME = "NEED TO WRITE"; /* Need to Write */
    private static final int DATABASE_VERSION = -1; /* Need to Write */

    public static DBHelper getInstance(Context context){
        if (instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }
    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.e("ACITIVITY LIFE CYCLE : " + TAG, "=========================CREATE DB===============");
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
