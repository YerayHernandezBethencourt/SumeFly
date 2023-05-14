package com.example.sumefly.conexionapi;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sumefly.R;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
public class MainActivity extends AppCompatActivity {
    private boolean isRecording = false;
    private OkHttpClient client = new OkHttpClient();
    private static final String TAG = "MainActivity";
    private static final int MAX_RETRIES = 3;
    private static final int RETRY_DELAY = 2000;
    private int retryCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_api_layout);
        String path = "flask está yendo y viniendo";
        RequestBody formBody = new FormBody.Builder()
                .add("path", path)
                .build();
        Request request = new Request.Builder()
                .url("http://127.0.0.1:5000/post") //posibles: 192.168.206.61 || 127.0.0.1 || 10.3.1.254
                .post(formBody)
                .build();

        // Configurar el tiempo de espera en la solicitud
        OkHttpClient clientWithTimeout = client.newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        // Enviar la solicitud HTTP con reintentos automáticos
        performRequestWithRetries(clientWithTimeout, request);

        isRecording = false;
    }

    private void performRequestWithRetries(OkHttpClient client, Request request) {
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (retryCount < MAX_RETRIES) {
                    retryCount++;
                    try {
                        Thread.sleep(RETRY_DELAY);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    performRequestWithRetries(client, request); // Realizar el reintentos
                } else {
                    handleConnectionFailure(e); // Se ha excedido el número máximo de reintentos
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                isRecording = true;
                final String responseBody = response.body().string();
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView respuesta = findViewById(R.id.tvGetApi);
                            respuesta.setText(responseBody);
                        }
                    });
                }finally {
                    response.close();
                }

            }
        });
    }

    private void handleConnectionFailure(IOException e) {
        Log.e(TAG, "Error en la conexión: " + e.getMessage());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                TextView tvError = findViewById(R.id.tvGetApi);
                tvError.setText("Error");
            }
        });
    }
}