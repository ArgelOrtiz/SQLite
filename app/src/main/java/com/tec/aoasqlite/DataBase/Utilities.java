package com.tec.aoasqlite.DataBase;

public class Utilities {

    public static final String EMPLOYEE_TABLE   =   "CREATE TABLE employee (" +
                                                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                    "code INTEGER,"+
                                                    "first_name VARCHAR(30)," +
                                                    "last_name VARCHAR(30)," +
                                                    "phone INTGER," +
                                                    "balance INTEGER)";

    public static final String EMPLOYEE             = "employee";
    public static final String EMPLOYEE_ID          = "id";
    public static final String EMPLOYEE_CODE        = "code";
    public static final String EMPLOYEE_FIRST_NAME  = "first_name";
    public static final String EMPLOYEE_LAST_NAME   = "last_name";
    public static final String EMPLOYEE_PHONNE      = "phone";
    public static final String EMPLOYEE_BALANCE     = "balance";

}
