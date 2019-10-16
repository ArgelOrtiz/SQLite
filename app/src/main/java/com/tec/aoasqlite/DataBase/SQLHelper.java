package com.tec.aoasqlite.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Vector;


public class SQLHelper extends SQLiteOpenHelper {


    public SQLHelper(Context context) {
        super(context, "data_base", null,1);
        // context, name, version
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE employee (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "code INTEGER,"+
                "first_name VARCHAR(30)," +
                "last_name VARCHAR(30)," +
                "phone INTGER," +
                "balance INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void save(String first_name, String last_name, int phone, int balance){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO employee VALUES " +
                "(null, '"+first_name+"', '"+last_name+"',"+phone +", "+balance+")");

    }

    public Vector<String> find(int code){
        Vector<String> result   = new Vector<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor   = db.rawQuery("SELECT code,first_name,last_name,phone,balance FROM employee" +
                "WHERE code = "+code,null);

        while (cursor.moveToNext()){
            result.add(cursor.getString(0)+" " +cursor.getString(1)+" "+cursor.getString(2)+""+
                        cursor.getInt(3)+" "+cursor.getInt(4));
        }

        cursor.close();
        return result;
    }
}
