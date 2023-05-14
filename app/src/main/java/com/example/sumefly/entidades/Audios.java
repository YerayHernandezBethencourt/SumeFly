package com.example.sumefly.entidades;

import java.sql.Blob;
import java.sql.Timestamp;

public class Audios {
    public Audios(int id, int id_record, int id_user, String title, Blob audio, Timestamp fecha) {
        this.id = id;
        this.id_record = id_record;
        this.id_user = id_user;
        this.title = title;
        this.audio = audio;
        this.fecha = fecha;
    }

    public Audios() {
    }

    private int id;
    private int id_record;
    private int id_user;
    private String title;
    private Blob audio;
    private Timestamp fecha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_record() {
        return id_record;
    }

    public void setId_record(int id_record) {
        this.id_record = id_record;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Blob getAudio() {
        return audio;
    }

    public void setAudio(Blob audio) {
        this.audio = audio;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public void setAudio(String string) {
    }
}
