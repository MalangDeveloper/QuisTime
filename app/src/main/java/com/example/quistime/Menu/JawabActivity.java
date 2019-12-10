package com.example.quistime.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quistime.Adapter.JawabMhsAdapter;
import com.example.quistime.Adapter.SoalDosenAdapter;
import com.example.quistime.MainActivity;
import com.example.quistime.Models.Mahasiswa;
import com.example.quistime.Models.Matkul;
import com.example.quistime.Models.Nilai;
import com.example.quistime.Models.SoalDosen;
import com.example.quistime.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.quistime.Menu.MhsLoginActivity.MHS;

public class JawabActivity extends AppCompatActivity {
    RecyclerView rvJawab;
    private ArrayList<SoalDosen> daftarSoal;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseReference database;
    String token, nama, NIM, kelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jawab);

        rvJawab= findViewById(R.id.rvJawab);
        layoutManager = new LinearLayoutManager(this);
        rvJawab.setLayoutManager(layoutManager);

        Bundle extras = getIntent().getExtras();
        Mahasiswa mhs = extras.getParcelable(MHS);
        nama = mhs.getNama();
        NIM = mhs.getNim();
        kelas = mhs.getKelas();
        token = mhs.getToken();

        database = FirebaseDatabase.getInstance().getReference();
        database.child("Matkul").child(token).child("Soal").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                daftarSoal = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    SoalDosen soalDosen = noteDataSnapshot.getValue(SoalDosen.class);
                    soalDosen.setKey(noteDataSnapshot.getKey());

                    daftarSoal.add(soalDosen);
                }
                adapter = new JawabMhsAdapter(daftarSoal, JawabActivity.this);
                rvJawab.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }

    public void onSaveData(Nilai n) {
        n.setMatkul(token);
        database.child("Nilai").child(NIM).push().setValue(n).addOnSuccessListener(JawabActivity.this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Intent intent = new Intent(JawabActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
