package com.tec.aoasqlite.CRUD;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.tec.aoasqlite.DataBase.Entities.Employee;
import com.tec.aoasqlite.DataBase.SQLHelper;
import com.tec.aoasqlite.R;

import java.util.Objects;

public class UpdateActivity extends AppCompatActivity {
    SQLHelper sqlHelper;

    TextInputLayout codeTextTextInputLayout, firstNameTextInputLayout, lastNameTextInputLayout, phoneTextInputLayout, balanceTextInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        initComponents();
    }

    private void initComponents(){
        final TextInputEditText codeTextInputEditTExt       = findViewById(R.id.codeUpdateTextInputEditTExt);
        final TextInputEditText firstNameTextInputEditText  = findViewById(R.id.firstNameUpdateTextInputEditText);
        final TextInputEditText lastNameTextInputEditText   = findViewById(R.id.lastNameUpdateTextInputEditText);
        final TextInputEditText phoneTextInputEditTExt      = findViewById(R.id.phoneUpdateTextInputEditTExt);
        final TextInputEditText balanceTextInputEditTExt    = findViewById(R.id.balanceUpdateTextInputEditTExt);
        codeTextTextInputLayout                             = findViewById(R.id.codeUpdateTextTextInputLayout);
        firstNameTextInputLayout                            = findViewById(R.id.firstNameUpdateTextInputLayout);
        lastNameTextInputLayout                             = findViewById(R.id.lastNameUpdateTextInputLayout);
        phoneTextInputLayout                                = findViewById(R.id.phoneUpdateTextInputLayout);
        balanceTextInputLayout                              = findViewById(R.id.balanceUpdateTextInputLayout);
        final Button searchButton                           = findViewById(R.id.searchUpdateButton);
        final Button updateButton                           = findViewById(R.id.updateButton);
        final Button deleteButton                           = findViewById(R.id.deleteUpdateButton);
        Button cleanButton                                  = findViewById(R.id.cleanUpdateButton);
        sqlHelper                                           =  new SQLHelper(getApplicationContext());

        firstNameTextInputLayout.setVisibility(View.GONE);
        lastNameTextInputLayout.setVisibility(View.GONE);
        phoneTextInputLayout.setVisibility(View.GONE);
        balanceTextInputLayout.setVisibility(View.GONE);
        deleteButton.setVisibility(View.GONE);
        updateButton.setVisibility(View.GONE);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstNameTextInputLayout.setVisibility(View.GONE);
                lastNameTextInputLayout.setVisibility(View.GONE);
                phoneTextInputLayout.setVisibility(View.GONE);
                balanceTextInputLayout.setVisibility(View.GONE);
                deleteButton.setVisibility(View.GONE);
                updateButton.setVisibility(View.GONE);

                long code;

                try {
                    code   = Long.parseLong(codeTextInputEditTExt.getText().toString());
                }catch (NumberFormatException e){
                    Log.e("UpdateActivity",e.getMessage());
                    code    = 0;
                }



                if (code == 0){
                    Snackbar.make(v,"Ingrese el codigo del empleado",Snackbar.LENGTH_LONG).show();
                    return;
                }

                Employee currentEmployee    = sqlHelper.findEmployee(code);

                if (currentEmployee == null){
                    Snackbar.make(v,"El empleado no existe",Snackbar.LENGTH_LONG).show();
                    return;
                }

                firstNameTextInputLayout.setVisibility(View.VISIBLE);
                lastNameTextInputLayout.setVisibility(View.VISIBLE);
                phoneTextInputLayout.setVisibility(View.VISIBLE);
                balanceTextInputLayout.setVisibility(View.VISIBLE);
                deleteButton.setVisibility(View.VISIBLE);
                updateButton.setVisibility(View.VISIBLE);

                firstNameTextInputEditText.setText(currentEmployee.getFirst_name());
                lastNameTextInputEditText.setText(currentEmployee.getLast_name());
                phoneTextInputEditTExt.setText(currentEmployee.getPhone()+"");
                balanceTextInputEditTExt.setText(currentEmployee.getBalance()+"");


                deleteButton.setVisibility(View.VISIBLE);

            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
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

                Employee currentEmployee    = new Employee(0,code,firstName,lastName,phone,balance);

                if (sqlHelper.update(currentEmployee)){
                    finish();
                    Snackbar.make(v,"Empleado actualizado",Snackbar.LENGTH_LONG).show();
                }else{
                    Snackbar.make(v,"Error al actualizar empleado",Snackbar.LENGTH_LONG).show();
                }


            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long code;

                try {
                    code   = Long.parseLong(codeTextInputEditTExt.getText().toString());
                }catch (NumberFormatException e){
                    Log.e("UpdateActivity",e.getMessage());
                    code    = 0;
                }



                if (code == 0){
                    Snackbar.make(v,"Ingrese el codigo del empleado",Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (sqlHelper.delete(code)){
                    finish();
                    Snackbar.make(v,"Empleado eliminado exitosamente",Snackbar.LENGTH_LONG).show();
                }else{
                    Snackbar.make(v,"Error al eliminar empleado",Snackbar.LENGTH_LONG).show();
                }

            }
        });

        cleanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstNameTextInputLayout.setVisibility(View.GONE);
                lastNameTextInputLayout.setVisibility(View.GONE);
                phoneTextInputLayout.setVisibility(View.GONE);
                balanceTextInputLayout.setVisibility(View.GONE);
                deleteButton.setVisibility(View.GONE);
                updateButton.setVisibility(View.GONE);

                codeTextInputEditTExt.setText("");
                firstNameTextInputEditText.setText("");
                lastNameTextInputEditText.setText("");
                phoneTextInputEditTExt.setText("");
                balanceTextInputEditTExt.setText("");

            }
        });

    }
}
