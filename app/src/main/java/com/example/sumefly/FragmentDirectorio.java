package com.example.sumefly;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sumefly.adapadores.ListaAudiosAdapter;
import com.example.sumefly.entidades.Audios;

import java.util.ArrayList;

public class FragmentDirectorio extends Fragment {

    ListaAudiosAdapter listaAudiosAdapter;
    RecyclerView recyclerView;
    ArrayList<Audios> listaAudios;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_directorio, container, false);
        recyclerView = vista.findViewById(R.id.listaAudios);
        listaAudios = new ArrayList<>();
        //cargamos la lista
        cargarLista();
        //mostramos los datos
        mostrarLista();
        return vista;
    }

    private void mostrarLista() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listaAudiosAdapter = new ListaAudiosAdapter(getContext(), listaAudios);
        recyclerView.setAdapter(listaAudiosAdapter);
    }

    public void cargarLista(){
        listaAudios.add(new Audios(1,1,1,"audio1",null,null));
        listaAudios.add(new Audios(2,1,1,"audio2",null,null));
    }
}
