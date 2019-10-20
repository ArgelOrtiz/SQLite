package com.tec.aoasqlite.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.tec.aoasqlite.DataBase.Entities.Employee;

import java.util.ArrayList;
import java.util.List;


public class SQLHelper extends SQLiteOpenHelper {


    public SQLHelper(Context context) {
        super(context, Environment.getExternalStorageDirectory()+"/data_base", null,1);
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

    public boolean update(Employee employee){
        SQLiteDatabase db = getWritableDatabase();

        String[] argumentos = {String.valueOf(employee.getCode())};

        ContentValues employeeContentValues = new ContentValues();
        employeeContentValues.put(Utilities.EMPLOYEE_CODE,employee.getCode());
        employeeContentValues.put(Utilities.EMPLOYEE_FIRST_NAME,employee.getFirst_name());
        employeeContentValues.put(Utilities.EMPLOYEE_LAST_NAME,employee.getLast_name());
        employeeContentValues.put(Utilities.EMPLOYEE_PHONNE,employee.getPhone());
        employeeContentValues.put(Utilities.EMPLOYEE_BALANCE,employee.getBalance());

        int result  = db.update(Utilities.EMPLOYEE,employeeContentValues,Utilities.EMPLOYEE_CODE+"=?",argumentos);

        return result != 0;
    }

    public boolean delete(long code){
        SQLiteDatabase db = getWritableDatabase();
        String[] argumentos = {String.valueOf(code)};

        int result  = db.delete(Utilities.EMPLOYEE,Utilities.EMPLOYEE_CODE+"=?",argumentos);

        return result != 0;
    }

    public List<Employee> listEmployees(){
        SQLiteDatabase db = getReadableDatabase();
        List<Employee> employeeList = new ArrayList<>();

        Cursor cursor   = db.rawQuery("SELECT * FROM employee ",null);

        try{

            while (cursor.moveToNext()){

                Employee currentEmployee    = new Employee(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getInt(5)
                );

                employeeList.add(currentEmployee);

            }

            cursor.close();
            return employeeList;

        }catch (Exception e){
            Log.e("MainActivity",e.getMessage());
            return null;

        }

    }

    public Employee findEmployee(long code){

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor   = db.rawQuery("SELECT * FROM employee " +
                "WHERE code == "+code+" LIMIT 1",null);

        try {

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

        }catch (Exception e){
            Log.e("UpdateActivity",e.getMessage());
            return null;
        }

    }
}
