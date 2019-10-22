package com.tec.aoasqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.tec.aoasqlite.CRUD.InsertActivity;
import com.tec.aoasqlite.CRUD.UpdateActivity;
import com.tec.aoasqlite.DataBase.Entities.Employee;
import com.tec.aoasqlite.DataBase.SQLHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    SQLHelper sqlHelper;
    RecyclerView employesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE },255
                    );
        }

        initComponents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void initComponents(){
        Button insertButton     = findViewById(R.id.insertMainButton);
        Button findButton       = findViewById(R.id.findMainButton);
        Button listButton       = findViewById(R.id.listMainButton);
        employesRecyclerView    = findViewById(R.id.employesMainRecyclerView);
        sqlHelper               =  new SQLHelper(getApplicationContext());

        employesRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false));

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent   = new Intent(getApplicationContext(), InsertActivity.class);
                startActivity(intent);
            }
        });

        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent   = new Intent(getApplicationContext(), UpdateActivity.class);
                startActivity(intent);
            }
        });

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getData();

            }
        });

    }

    protected void getData(){
        List<Employee> employeeList = sqlHelper.listEmployees();

        if (employeeList != null){
            employesRecyclerView.setAdapter(new EmployeRecyclerViewAdapter(employeeList));
        }else {
            Toast.makeText(getApplicationContext(),"No hay empleados",Toast.LENGTH_LONG).show();
        }
    }
}
