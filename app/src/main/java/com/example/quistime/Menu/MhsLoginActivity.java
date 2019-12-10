package com.example.quistime.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quistime.Models.Login;
import com.example.quistime.Models.Mahasiswa;
import com.example.quistime.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MhsLoginActivity extends AppCompatActivity {
    public static final String MHS = "Mahasiswa";
    private EditText txtNama, txtNIM,txtKelas;
    private Button btnLogin;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mhs_login);

        txtNama = findViewById(R.id.txtNama);
        txtNIM = findViewById(R.id.txtNIM);
        txtKelas = findViewById(R.id.txtKelas);
        btnLogin = findViewById(R.id.btnLogin);
        database = FirebaseDatabase.getInstance().getReference();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
    }
    private void Login(){
        final String nama = txtNama.getText().toString().trim();
        final String kelas = txtKelas.getText().toString().trim();
        final String nim = txtNIM.getText().toString().trim();

        if (nama.isEmpty()){
            txtNama.setError("Isikan Nama");
        }else if (kelas.isEmpty()){
            txtKelas.setError("Isikan Kelas");
        }else if (nim.isEmpty()){
            txtNIM.setError("Isikan NIM");
        }else if (nim.length() == 9){
            txtNIM.setError("NIM terdiri dari 10 Karakter");
        }else {
            final Mahasiswa mhs = new Mahasiswa(nama,kelas,nim);
            database.child("Mahasiswa").child(nim).setValue(mhs).addOnSuccessListener(MhsLoginActivity.this, new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Intent intent = new Intent(MhsLoginActivity.this,TokenActivity.class);
                    intent.putExtra(MHS, mhs);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
}
