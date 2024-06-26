package com.gael.t1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityLogin extends AppCompatActivity {
    Button btningresar;
    EditText etUser, etPwd;
    AdminSQLiteOpenHelper dbsqlite;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //BASE DE DATOS
        dbsqlite = new AdminSQLiteOpenHelper(this);
        //VARIABLES DEL USUARIO,CONTRASEÑA,BOTON
        etUser = findViewById(R.id.etUser);
        etPwd = findViewById(R.id.etPwd);
        btningresar = findViewById(R.id.btningresar);
        //BOTONES DE REDES SOCIALES
        ImageView Imageninstagram = findViewById(R.id.instagram);
        ImageView Imagenfacebook = findViewById(R.id.facebook);
        ImageView ImagenX = findViewById(R.id.X);

        //CLICK EN LOS BOTONES DE REDES SOCIALES
        //INSTAGRAM
        Imageninstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.instagram.com/botiapp_p/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        //FACEBOOK
        Imagenfacebook.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String url ="https://www.facebook.com/profile.php?id=61559286806418";
                Intent intent =new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        //X
        ImagenX.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String url ="https://x.com/AppBotiG4?t=ryshndEPCp_m4Y650iImmg&s=09";
                Intent intent =new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        //CLICK EN EL BOTON INGRESAR
        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUser.getText().toString();
                String password = etPwd.getText().toString();
                boolean isLoggedIn = dbsqlite.checkUser(username, password);

                if (isLoggedIn) {
                    Intent intent = new Intent(ActivityLogin.this, InicioActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                } else {
                    Toast.makeText(ActivityLogin.this, "Fallo al ingresar", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
