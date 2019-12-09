package com.example.quistime.Models;

public class Mahasiswa {
    private String nama,kelas,nim, token;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Mahasiswa(String nama, String kelas, String nim, String token) {
        this.nama = nama;
        this.kelas = kelas;
        this.nim = nim;
        this.token = token;
    }

    public Mahasiswa(String nama, String kelas, String nim) {
        this.nama = nama;
        this.kelas = kelas;
        this.nim = nim;
    }

    public Mahasiswa(){

    }
}
