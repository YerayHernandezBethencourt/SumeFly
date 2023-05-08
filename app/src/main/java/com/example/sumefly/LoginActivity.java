package com.example.sumefly;

import static com.example.sumefly.database.DbHelper.COLUMN_EMAIL;
import static com.example.sumefly.database.DbHelper.COLUMN_PASSWORD;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sumefly.database.DbHelper;
import com.example.sumefly.database.DbUsuario;

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
                if(!comprobarInicioSesion()){
                    Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Bienvenido", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    //Comprobar los datos del login
    private boolean comprobarInicioSesion() {
        gmail = email.getText().toString();
        gPass = password.getText().toString();

        //Se instancia nuestra base de datos
        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                COLUMN_EMAIL,
                COLUMN_PASSWORD
        };

        String selection = "email = ? AND password = ?";

        String[] selectionArgs = {gmail, gPass};

        Cursor cursor = db.query(
                DbUsuario.TABLE_USER,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if(cursor != null && cursor.moveToFirst()){
            do{
                String valorEmail = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL));
                String valorPass = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD));

                System.out.println("Email: " + valorEmail + " Password: " + valorPass);
                if(valorEmail.equals(gmail) && valorPass.equals(gPass)){
                    cursor.close();
                    db.close();
                    return true;
                }
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return false;
    }

}
