package com.example.sumefly.fragments;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sumefly.R;
import com.example.sumefly.adapadores.ListaAudiosAdapter;
import com.example.sumefly.entidades.Audios;

import java.util.ArrayList;

public class FragmentListaAudios extends Fragment implements View.OnClickListener {
    ArrayList<Audios> listaArrayAudios;
    LayoutInflater inflater;
    //listener
    private View.OnClickListener listener;

    public void ListaAudiosAdapter(Context context, ArrayList<Audios> listaArrayAudios) {
        this.inflater = LayoutInflater.from(context);
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

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
    }

    @NonNull
    public ListaAudiosAdapter.AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_item, parent, false);
        return new ListaAudiosAdapter.AudioViewHolder(view);
    }


    public void onBindViewHolder(@NonNull ListaAudiosAdapter.AudioViewHolder holder, int position) {
        holder.etNombreAudio.setText(listaArrayAudios.get(position).getTitle());
        holder.fechaAudio.setText(listaArrayAudios.get(position).getFecha().toString());
    }

    public int getItemCount() {
        return listaArrayAudios.size();
    }
}
