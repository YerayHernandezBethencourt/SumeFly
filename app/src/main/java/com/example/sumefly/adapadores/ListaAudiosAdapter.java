package com.example.sumefly.adapadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sumefly.R;
import com.example.sumefly.entidades.Audios;

import java.util.ArrayList;

public class ListaAudiosAdapter extends RecyclerView.Adapter<ListaAudiosAdapter.AudioViewHolder>{

    ArrayList<Audios> listaArrayAudios;

    public ListaAudiosAdapter(ArrayList<Audios> listaArrayAudios) {
        this.listaArrayAudios = listaArrayAudios;
    }

    public class AudioViewHolder extends RecyclerView.ViewHolder {

        EditText etNombreAudio;
        TextView fechaAudio;
        public AudioViewHolder(@NonNull View itemView) {
            super(itemView);
            etNombreAudio = itemView.findViewById(R.id.etTitleElement);
            fechaAudio = itemView.findViewById(R.id.txtFecha);
        }
    }

    @NonNull
    @Override
    public ListaAudiosAdapter.AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_item, null, false);
        return new AudioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaAudiosAdapter.AudioViewHolder holder, int position) {

        holder.etNombreAudio.setText(listaArrayAudios.get(position).getTitle());
        holder.fechaAudio.setText(listaArrayAudios.get(position).getFecha().toString());

    }

    @Override
    public int getItemCount() {
        return listaArrayAudios.size();
    }
}
