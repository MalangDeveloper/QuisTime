package com.example.quistime.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Mahasiswa implements Parcelable {
    private String nama,kelas,nim, token;

    protected Mahasiswa(Parcel in) {
        nama = in.readString();
        kelas = in.readString();
        nim = in.readString();
        token = in.readString();
    }

    public static final Creator<Mahasiswa> CREATOR = new Creator<Mahasiswa>() {
        @Override
        public Mahasiswa createFromParcel(Parcel in) {
            return new Mahasiswa(in);
        }

        @Override
        public Mahasiswa[] newArray(int size) {
            return new Mahasiswa[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nama);
        parcel.writeString(kelas);
        parcel.writeString(nim);
        parcel.writeString(token);
    }
}
