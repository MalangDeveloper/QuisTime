package com.example.quistime.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Matkul implements Parcelable {
    private String matkul;
    private String code;
    private String tanggal;
    private String key;

    public Matkul(String matkul, String code, String tanggal) {
        this.matkul = matkul;
        this.code = code;
        this.tanggal = tanggal;
    }

    public Matkul(String matkul, String key) {
        this.matkul = matkul;
        this.key = key;
    }

    public  Matkul(){

    }

    protected Matkul(Parcel in) {
        matkul = in.readString();
        code = in.readString();
        tanggal = in.readString();
        key = in.readString();
    }

    public static final Creator<Matkul> CREATOR = new Creator<Matkul>() {
        @Override
        public Matkul createFromParcel(Parcel in) {
            return new Matkul(in);
        }

        @Override
        public Matkul[] newArray(int size) {
            return new Matkul[size];
        }
    };

    public String getMatkul() {
        return matkul;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(matkul);
        parcel.writeString(code);
        parcel.writeString(tanggal);
        parcel.writeString(key);
    }
}
