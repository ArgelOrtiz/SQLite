package com.tec.aoasqlite.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tec.aoasqlite.DataBase.Entities.Employee;

import java.util.ArrayList;
import java.util.List;


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

    public void save(Employee employee){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO employee VALUES " +
                "(null,"+ employee.getCode()+" ,'"+employee.getFirst_name()+"', '"+employee.getLast_name()+"',"+employee.getPhone() +", "+employee.getBalance()+")");

    }

    public Employee findEmployee(int code){

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor   = db.rawQuery("SELECT * FROM employee " +
                "WHERE code == "+code+" LIMIT 1",null);

        cursor.moveToNext();

        Employee currentEmployee    = new Employee(
                cursor.getInt(0),
                cursor.getInt(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4),
                cursor.getInt(5)
        );

        cursor.close();
        return currentEmployee;
    }
}
