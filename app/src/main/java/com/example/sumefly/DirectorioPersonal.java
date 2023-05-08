package com.example.sumefly;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sumefly.adapadores.ListaAudiosAdapter;
import com.example.sumefly.database.DBAudio;

import java.util.ArrayList;

public class DirectorioPersonal extends AppCompatActivity {

    EditText etNombreAudio;
    TextView fechaAudio;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.directorio_personal);
    }
}
