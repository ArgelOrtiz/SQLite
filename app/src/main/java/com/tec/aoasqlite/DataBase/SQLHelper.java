package com.tec.aoasqlite.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tec.aoasqlite.DataBase.Entities.Employee;


public class SQLHelper extends SQLiteOpenHelper {


    public SQLHelper(Context context) {
        super(context, "data_base", null,1);
        // context, name, version
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilities.EMPLOYEE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insert(Employee employee){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues employeeContentValues = new ContentValues();
        employeeContentValues.put(Utilities.EMPLOYEE_CODE,employee.getCode());
        employeeContentValues.put(Utilities.EMPLOYEE_FIRST_NAME,employee.getFirst_name());
        employeeContentValues.put(Utilities.EMPLOYEE_LAST_NAME,employee.getLast_name());
        employeeContentValues.put(Utilities.EMPLOYEE_PHONNE,employee.getPhone());
        employeeContentValues.put(Utilities.EMPLOYEE_BALANCE,employee.getBalance());

        return db.insert(Utilities.EMPLOYEE,Utilities.EMPLOYEE_ID,employeeContentValues);

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
