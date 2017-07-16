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
            Log.v("rht","aaaaaaaaaaaaaaaaaaaa.....oncreate....aaaaaaaaaaaaaaaaaaaaaa***************");
//            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_LOCATIONS);
        }catch (SQLException e){
//            Toast.makeText(,"connection failed", Toast.LENGTH_SHORT).show();
            Log.v("rht","aaaaaaaaaaaaaaaaaaaa.....failed....aaaaaaaaaaaaaaaaaaaaaa***************");
        }
//        addIP("");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_IP);
        onCreate(db);
    }

    // Add a  new location to the table
    public void addIP(String ip){
        ContentValues values = new ContentValues();
        values.put(COLUMN_IP,ip);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_IP,null,values);
        Log.v("rht","aaaaaaaaaaaaaaaaaaaa.....adding....aaaaaaaaaaaaaaaaaaaaaa***************");
        db.close();
        Log.v("rht","aaaaaaaaaaaaaaaaaaaa.....closed....aaaaaaaaaaaaaaaaaaaaaa***************");
    }

    public  void updateIP(String ip){
        SQLiteDatabase db = getWritableDatabase();
        String id="1";
        Log.v("rht","aaaaaaaaaaaaaaaaaaaa.....updating report....aaaaaaaaaaaaaaaaaaaaaa***************");
        db.execSQL("UPDATE "+ TABLE_IP+ " SET ip = \""+ip+"\" WHERE "+ COLUMN_ID+ " =\"" + id +"\";");
        db.close();
    }


    public String getIP(){
        String ip="";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_IP+ " WHERE 1 ";
        Log.v("rht","aaaaaaaaaaaaaaaaaaaa.....start cursur....aaaaaaaaaaaaaaaaaaaaaa***************");
        // Cursor points to a location in your results
        Cursor c = db.rawQuery(query,null);
        Log.v("rht","aaaaaaaaaaaaaaaaaaaa.....intiated cursur....aaaaaaaaaaaaaaaaaaaaaa***************");
        if(c.moveToFirst()){
            do{
                Log.v("rht","aaaaaaaaaaaaaaaaaaaa.....inside while cursur....aaaaaaaaaaaaaaaaaaaaaa***************"+c.getString(c.getColumnIndex("id"))+" "+c.getString(c.getColumnIndex("ip")));
//                Log.v("rht","aaaaaaaaaaaaaaaaaaaa.....inside while cursur....aaaaaaaaaaaaaaaaaaaaaa***************"+);
//                String column1 = c.getString(2);
                ip=c.getString(c.getColumnIndex("ip"));

            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return ip;
    }

}
