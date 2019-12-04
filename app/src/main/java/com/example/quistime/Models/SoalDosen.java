package com.example.quistime.Models;

public class SoalDosen {
    private String Soal, a, b, c, d, e, jawaban, key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public SoalDosen(String soal, String a, String b, String c, String d, String e, String jawaban) {
        Soal = soal;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.jawaban = jawaban;
    }

    public SoalDosen(String soal, String a, String b, String c, String d, String e) {
        Soal = soal;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }

    public SoalDosen(){

    }

    public String getSoal() {
        return Soal;
    }

    public void setSoal(String soal) {
        Soal = soal;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }
}
