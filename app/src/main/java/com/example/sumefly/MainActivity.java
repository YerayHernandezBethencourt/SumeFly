package com.example.sumefly;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sumefly.database.DBAudio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MediaRecorder grabadora = null;
    int estado = 0;     //0: no grabando, 1: grabando, 2: pausado
    String ruta = null;
    ImageView imgGrabar = null;
    ImageView imgDetener = null;
    ImageView imgPausar = null;
    List<ListElement> listElements;

    private Chronometer crono;
    private long tiempo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crono = findViewById(R.id.crono);
        Context context = getApplicationContext();

        // Verifica si los permisos están concedidos
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Solicita los permisos
            requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            System.out.println("Solicita los permisos");
        } else {
            // Los permisos ya están concedidos, puedes realizar las acciones que requieren los permisos
            System.out.println("Los permisos ya están concedidos, puedes realizar las acciones que requieren los permisos");
        }
        init();
    }
    //YERAY. ESTO MAÑANA LO HACEMOS
    //de momento lo declaramos los datos de forma manual
    //Luego tendremos que recogerlo de la base de datos
    public void init(){
        imgGrabar = findViewById(R.id.imgGrabar);
        imgDetener = findViewById(R.id.imgDetener);
        imgPausar = findViewById(R.id.imgPausar);

        listElements = new ArrayList<>();
        listElements.add(new ListElement("Titulo 1", "Fecha 1"));
        listElements.add(new ListElement("Titulo 2", "Fecha 2"));
        listElements.add(new ListElement("Titulo 3", "Fecha 3"));

        ListAdapter listAdapter = new ListAdapter(listElements, this);
        RecyclerView recyclerView = findViewById(R.id.recycleviewList);
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(listAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }
    //Metodos para la grabacion
    //Método para comenzar la grabacion
    public void grabar(View view){
        //Si no hay una grabacion en curso
        if(grabadora == null){
            if(estado == 0){
                //Se cambia el estado a grabando
                estado = 1;
                //Se crea una nueva grabadora
                grabadora = new MediaRecorder();
                //Se le asigna la ruta donde se guardara el archivo
                ruta = getExternalFilesDir(null).getAbsolutePath() + "/grabacion.mp3";
                //Se le asigna el microfono como fuente de audio
                grabadora.setAudioSource(MediaRecorder.AudioSource.MIC);
                //Se le asigna el formato de salida
                grabadora.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                //Se le asigna el codificador de audio
                grabadora.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                //Se le asigna la ruta donde se guardara el archivo
                grabadora.setOutputFile(ruta);
                //Se prepara la grabadora
                try {
                    grabadora.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Se reinicia el cronometro
                crono.setBase(SystemClock.elapsedRealtime() - tiempo);
                //Se inicia la grabacion
                grabadora.start();
                //Comienza el cronometro
                crono.start();
            } else if(estado == 2){
                //Se reanuda la grabacion
                grabadora.resume();
                //Se reanuda el cronometro
                crono.start();
                //Se cambia el estado a grabando
                estado = 1;
            }
            //Se oscurece la imagen del boton de grabar
            imgGrabar.setImageResource(R.drawable.image_play2);
            //Se acalara la imagen del boton de pausar
            imgPausar.setImageResource(R.drawable.pause_image);
            //Se aclara el boton de detener
            imgDetener.setImageResource(R.drawable.stop_image);

            //Se deshabilita el boton de pausar
            imgGrabar.setEnabled(false);
            //Se habilita el boton de detener
            imgDetener.setEnabled(true);
            //Se habilita el botón de pausar
            imgPausar.setEnabled(true);
            //Se muestra un mensaje
            Toast.makeText(this, "Grabando...", Toast.LENGTH_SHORT).show();
        }else if(grabadora != null && estado == 2){
            //Se cambia el estado a grabando
            estado = 1;
            //Se continua la grabacion
            grabadora.resume();
            //Comienza el cronometro
            crono.start();
            //Continua el cronómetro
            crono.setBase(SystemClock.elapsedRealtime() - tiempo);
            //Se oscurece la imagen del boton de grabar
            imgGrabar.setImageResource(R.drawable.image_play2);
            //Se acalara la imagen del boton de pausar
            imgPausar.setImageResource(R.drawable.pause_image);
            //Se aclara el boton de detener
            imgDetener.setImageResource(R.drawable.stop_image);

            //Se deshabilita el boton de grabar
            imgGrabar.setEnabled(false);
            //Se habilita el boton de detener
            imgDetener.setEnabled(true);
            //Se habilita el botón de pausar
            imgPausar.setEnabled(true);
            //Se muestra un mensaje
            Toast.makeText(this, "Continuando la grabacion", Toast.LENGTH_SHORT).show();
        }
    }
    //Metodo para pausar la grabacion
    public void pausar(View view){
        //Si hay una grabacion en curso
        if(grabadora != null){
            //Se cambia el estado a pausado
            estado = 2;
            //Se pausa la grabacion
            grabadora.pause();
            //Se oscurece la imagen del boton de pausar
            imgPausar.setImageResource(R.drawable.image_pause2);
            //Se aclara la imagen del boton play
            imgGrabar.setImageResource(R.drawable.play_image);
            //Se habilita el boton de grabar
            imgGrabar.setEnabled(true);
            //Se deshabilita el boton de pausar
            imgPausar.setEnabled(false);
            //Se pausa el cronómetro
            crono.stop();
            //Se guarda el tiempo actual
            tiempo = SystemClock.elapsedRealtime() - crono.getBase();
            //Se muestra un mensaje
            Toast.makeText(this, "Pausado...", Toast.LENGTH_SHORT).show();
        }
    }
    //Metodo para detener la grabacion
    public void detenerGrabacion(View view) {
        //Si hay una grabacion en curso
        if (grabadora != null) {
            //Se detiene la grabacion
            grabadora.stop();
            //Se libera la grabadora
            grabadora.release();
            //Se asigna null a la grabadora
            grabadora = null;
            //Se aclara la imagen del boton de grabar
            imgGrabar.setImageResource(R.drawable.play_image);
            //Se aclara la imagen del boton de pausar
            imgPausar.setImageResource(R.drawable.pause_image);
            //Se oscurece la imagen del boton de detener
            imgDetener.setImageResource(R.drawable.stop_image2);
            //Se habilita el boton de grabar
            imgGrabar.setEnabled(true);
            //Se deshabilita el boton de pausar
            imgPausar.setEnabled(false);
            //Se deshabilita el boton de detener
            imgDetener.setEnabled(false);
            //Se detiene el cronometro
            crono.stop();
            //Se reinicia el cronometro
            crono.setBase(SystemClock.elapsedRealtime());
            tiempo = 0;
            //Se establece el estado a detenido
            estado = 0;
            //Se instancia nuestra base de datos
            DBAudio dbAudio = new DBAudio(this);
            //Se crea un objeto de tipo Audio
            dbAudio.insertarAudio(3, 101, "titulo provisional", ruta);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu:
                losRegistro();
                return true;
            case R.id.menu2:
                nuevaGrabacion();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void losRegistro(){
        Intent intent = new Intent(this, ListElement.class);
        startActivity(intent);
    }
    public void nuevaGrabacion(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}