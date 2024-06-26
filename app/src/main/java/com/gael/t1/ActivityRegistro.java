package com.gael.t1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityRegistro extends AppCompatActivity {
    EditText etUser, etPwd, etRepwd;
    Button btnregistrar, btningresar;
    AdminSQLiteOpenHelper dbsqlite;
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        etUser = findViewById(R.id.etUsername);
        etPwd = findViewById(R.id.etPassword);
        etRepwd = findViewById(R.id.etRePassword);
        btnregistrar = findViewById(R.id.btn_registrar);
        dbsqlite = new AdminSQLiteOpenHelper(this);
        btningresar = findViewById(R.id.btn_ingresar);
        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityRegistro.this, ActivityLogin.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user, pwd, rePwd;
                user = etUser.getText().toString();
                pwd = etPwd.getText().toString();
                rePwd = etRepwd.getText().toString();
                if (user.equals("") || pwd.equals("") || rePwd.equals("") ){
                    Toast.makeText(ActivityRegistro.this,"Por favor ingresa los datos de manera correcta", Toast.LENGTH_LONG).show();
                } else {
                    if(pwd.equals(rePwd)){
                        if (dbsqlite.checkUsername(user)){
                            Toast.makeText(ActivityRegistro.this,"El Usuario ya existe", Toast.LENGTH_LONG).show();
                            return;
                        }
                        boolean registeredSuccess = dbsqlite.insertData(user,pwd);
                        if(registeredSuccess)
                            Toast.makeText(ActivityRegistro.this,"Se registró de manera correcta", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(ActivityRegistro.this,"No se registró el usuario", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(ActivityRegistro.this,"Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btningresar = findViewById(R.id.btn_ingresar);
    }
}