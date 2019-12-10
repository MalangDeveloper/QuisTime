package com.example.quistime.Models;

public class Nilai {
    public String nilai, tanggal, matkul;

    public Nilai(String nilai, String tanggal) {
        this.nilai = nilai;
        this.tanggal = tanggal;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    public String getMatkul() {
        return matkul;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }
}
