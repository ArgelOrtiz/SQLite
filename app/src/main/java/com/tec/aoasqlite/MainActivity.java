package com.tec.aoasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tec.aoasqlite.DataBase.Entities.Employee;
import com.tec.aoasqlite.DataBase.SQLHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    SQLHelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
    }

    private void initComponents(){
        sqlHelper   = new SQLHelper(getApplicationContext());

        test();
    }

    private void test(){
        Employee currentEmployee    = new Employee();

        currentEmployee.setCode(001);
        currentEmployee.setFirst_name("Argel");
        currentEmployee.setLast_name("Ortiz");
        currentEmployee.setPhone(477123456);
        currentEmployee.setBalance(100);

        sqlHelper.save(currentEmployee);

        currentEmployee    = new Employee();
        currentEmployee.setCode(002);
        currentEmployee.setFirst_name("Daniel");
        currentEmployee.setLast_name("Tavarez");
        currentEmployee.setPhone(477123456);
        currentEmployee.setBalance(100);

        sqlHelper.save(currentEmployee);

        Employee employeeList = sqlHelper.findEmployee(001);

        System.err.println(employeeList.getFirst_name());

    }
}
