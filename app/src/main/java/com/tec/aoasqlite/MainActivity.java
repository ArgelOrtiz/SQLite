package com.tec.aoasqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tec.aoasqlite.CRUD.InsertActivity;
import com.tec.aoasqlite.DataBase.Entities.Employee;
import com.tec.aoasqlite.DataBase.SQLHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView employesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
    }

    private void initComponents(){
        Button insertButton     = findViewById(R.id.insertMainButton);
        Button findButton       = findViewById(R.id.findMainButton);
        Button listButton       = findViewById(R.id.listMainButton);
        employesRecyclerView    = findViewById(R.id.employesMainRecyclerView);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent   = new Intent(getApplicationContext(), InsertActivity.class);
                startActivity(intent);
            }
        });

    }
}
