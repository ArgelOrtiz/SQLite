package com.tec.aoasqlite.CRUD;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.tec.aoasqlite.DataBase.Entities.Employee;
import com.tec.aoasqlite.DataBase.SQLHelper;
import com.tec.aoasqlite.R;

import java.util.Objects;

public class InsertActivity extends AppCompatActivity {

    SQLHelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        initComponents();
    }

    private void initComponents(){
        final TextInputEditText codeTextInputEditTExt       = findViewById(R.id.codeInsertTextInputEditTExt);
        final TextInputEditText firstNameTextInputEditText  = findViewById(R.id.firstNameInsertTextInputEditText);
        final TextInputEditText lastNameTextInputEditText   = findViewById(R.id.lastNameInsertTextInputEditText);
        final TextInputEditText phoneTextInputEditTExt      = findViewById(R.id.phoneInsertTextInputEditTExt);
        final TextInputEditText balanceTextInputEditTExt    = findViewById(R.id.balanceInsertTextInputEditTExt);
        Button inserButton                                  = findViewById(R.id.inserButton);
        sqlHelper                                           =  new SQLHelper(getApplicationContext());


        inserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int code            = Integer.parseInt(Objects.requireNonNull(codeTextInputEditTExt.getText()).toString());
                String firstName    = Objects.requireNonNull(firstNameTextInputEditText.getText()).toString();
                String lastName     = Objects.requireNonNull(lastNameTextInputEditText.getText()).toString();
                long phone          = Long.parseLong(Objects.requireNonNull(phoneTextInputEditTExt.getText()).toString());
                int balance         = Integer.parseInt(Objects.requireNonNull(balanceTextInputEditTExt.getText()).toString());

                if (code == 0){
                    Snackbar.make(v,"Ingrese un codigo",Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (firstName.isEmpty()){
                    Snackbar.make(v,"Ingrese su nombre",Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (lastName.isEmpty()){
                    Snackbar.make(v,"Ingrese sus apellidos",Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (phone == 0){
                    Snackbar.make(v,"Ingrese su numero de telefono",Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (balance == 0){
                    Snackbar.make(v,"Ingrese el saldo",Snackbar.LENGTH_LONG).show();
                    return;
                }

                Employee newEmployee    = new Employee(0,code,firstName,lastName,phone,balance);

                long result = sqlHelper.insert(newEmployee);

                if (result  > 0){
                    Snackbar.make(v,"Se agrego con exito",Snackbar.LENGTH_LONG).show();
                    finish();
                }else {
                    Snackbar.make(v,"Ocurrio un error al agregar el empleado",Snackbar.LENGTH_LONG).show();
                }


            }
        });
    }
}
