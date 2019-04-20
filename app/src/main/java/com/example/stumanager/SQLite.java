package com.example.stumanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class SQLite extends SQLiteOpenHelper {
    private static final String STU="student";
    private static final String COU="course";
    private static final String CHO="choose";

    private Context mContext;
    public SQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
           String sql1="CREATE TABLE IF NOT EXISTS "+STU+"(_id INTEGER PRIMARY KEY AUTOINCREMENT,stuid VARCHAR(50),stupass VARCHAR(50),stuname VARCHAR(50),stuage VARCHAR(50),stusex VARCHAR(50),stuphone VARCHAR(50))";
           String sql2="CREATE TABLE IF NOT EXISTS "+COU+"(_id INTEGER PRIMARY KEY AUTOINCREMENT,couid VARCHAR(50),couname VARCHAR(50),coucredit VARCHAR(50),counum VARCHAR(50))";
           String sql3="CREATE TABLE IF NOT EXISTS "+CHO+"(_id INTEGER PRIMARY KEY AUTOINCREMENT,stuid VARCHAR(50),stuname VARCHAR(50),couid VARCHAR(50),couname VARCHAR(50),couscore VARCHAR(50))";
           db.execSQL(sql1);
           db.execSQL(sql2);
           db.execSQL(sql3);
           //Toast.makeText(mContext,"Create succeeded",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table "+STU);
        db.execSQL("drop table "+COU);
        db.execSQL("drop table "+CHO);
        onCreate(db);
    }

}
