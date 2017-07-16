package com.codemo.www.mc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;

/**
 * Created by root on 4/14/17.
 */

public class DatabaseController extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "ipaddress.db";
    private static final String TABLE_IP = "ipaddress";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_IP = "ip";

    public DatabaseController(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_IP);
        String query = "CREATE TABLE " + TABLE_IP + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_IP + " TEXT " +
                ");";
        try {
            db.execSQL(query);
        }catch (SQLException e){
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_IP);
        onCreate(db);
    }

    // Add a  new ip to the table
    public void addIP(String ip){
        ContentValues values = new ContentValues();
        values.put(COLUMN_IP,ip);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_IP,null,values);
        db.close();
    }

    public  void updateIP(String ip){
        SQLiteDatabase db = getWritableDatabase();
        String id="1";
        db.execSQL("UPDATE "+ TABLE_IP+ " SET ip = \""+ip+"\" WHERE "+ COLUMN_ID+ " =\"" + id +"\";");
        db.close();
    }


    public String getIP(){
        String ip="";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_IP+ " WHERE 1 ";
        // Cursor points to a location in your results
        Cursor c = db.rawQuery(query,null);
        if(c.moveToFirst()){
            do{
                ip=c.getString(c.getColumnIndex("ip"));
            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return ip;
    }

}
