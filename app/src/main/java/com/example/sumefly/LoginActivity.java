package com.example.sumefly;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.python.util.PythonInterpreter;

public class LoginActivity extends AppCompatActivity {

    String gmail, gPass;
    EditText email;
    EditText password;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Context context = getApplicationContext();


        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPass);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobarInicioSesion();
            }
        });
        gmail = email.getText().toString();
        gPass = password.getText().toString();
    }
    //Comprobar los datos del login
    private void comprobarInicioSesion() {
        gmail = email.getText().toString();
        gPass = password.getText().toString();
        if(gmail.equals("admin") && gPass.equals("admin")){
            //Mostrar mensaje de bienvenida
            Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else{
            //Mostrar mensaje de error
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }


}
