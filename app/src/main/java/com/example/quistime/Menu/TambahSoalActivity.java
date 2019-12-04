package com.example.quistime.Menu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quistime.Models.Matkul;
import com.example.quistime.Models.SoalDosen;
import com.example.quistime.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.quistime.Adapter.MatkulDosenAdapter.MATKUL;

public class TambahSoalActivity extends AppCompatActivity {
    private EditText txtTsoal,txtTA, txtTB, txtTC, txtTD, txtTE;
    private Spinner txtkunci;
    private Button btnTambah;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_soal);

        txtTsoal = findViewById(R.id.txtTSoal);
        txtTA = findViewById(R.id.txtTA);
        txtTB = findViewById(R.id.txtTB);
        txtTC = findViewById(R.id.txtTC);
        txtTD = findViewById(R.id.txtTD);
        txtTE = findViewById(R.id.txtTE);
        txtkunci = findViewById(R.id.spinnerKunci);
        btnTambah = findViewById(R.id.btnTambah);

        database = FirebaseDatabase.getInstance().getReference();
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ambilData();
            }
        });
    }
    private void ambilData(){
        final String soal = txtTsoal.getText().toString().trim();
        final String tmbA = txtTA.getText().toString().trim();
        final String tmbB = txtTB.getText().toString().trim();
        final String tmbC = txtTC.getText().toString().trim();
        final String tmbD = txtTD.getText().toString().trim();
        final String tmbE = txtTE.getText().toString().trim();
        final String kunci = txtkunci.getSelectedItem().toString();

        if (soal.isEmpty()){
            txtTsoal.setError("Masukkan Soal");
        }else if (tmbA.isEmpty()){
            txtTA.setError("Masukkan Jawaban A");
        }else if (tmbB.isEmpty()){
            txtTB.setError("Masukkan Jawaban B");
        }else if (tmbC.isEmpty()){
            txtTC.setError("Masukkan Jawaban C");
        }else if (tmbD.isEmpty()){
            txtTD.setError("Masukkan Jawaban D");
        }else {
            tambahSoal(new SoalDosen(soal, tmbA, tmbB, tmbC, tmbD, tmbE, kunci));
        }
    }
    private void tambahSoal(SoalDosen soalDosen){
        Bundle extras = getIntent().getExtras();
        Matkul m = extras.getParcelable(MATKUL);

        database.child("Matkul").child(m.getKey()).child("Soal").push().setValue(soalDosen).addOnSuccessListener(TambahSoalActivity.this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(TambahSoalActivity.this, "Berhasil Tambah Soal", Toast.LENGTH_SHORT).show();
            }
        });
        Intent intent = new Intent(TambahSoalActivity.this,SoalActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(MATKUL, m);
        startActivity(intent);
        finish();
    }
}
