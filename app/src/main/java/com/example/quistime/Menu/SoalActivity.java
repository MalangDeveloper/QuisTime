package com.example.quistime.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quistime.Adapter.SoalDosenAdapter;
import com.example.quistime.Models.Matkul;
import com.example.quistime.Models.SoalDosen;
import com.example.quistime.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.quistime.Adapter.MatkulDosenAdapter.MATKUL;

public class SoalActivity extends AppCompatActivity {
    Button btnTambah;
    RecyclerView rvSoal;
    private ArrayList<SoalDosen> daftarSoal;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);

        btnTambah = findViewById(R.id.btnTambahSoal);
        rvSoal= findViewById(R.id.rvSoal);
        layoutManager = new LinearLayoutManager(this);
        rvSoal.setLayoutManager(layoutManager);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Bundle extras = getIntent().getExtras();
//                    Matkul m = extras.getParcelable(MATKUL);
                Intent intent = new Intent(SoalActivity.this,TambahSoalActivity.class);
                startActivity(intent);
            }
        });

        database = FirebaseDatabase.getInstance().getReference();
        database.child("SoalDosen").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                daftarSoal = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    SoalDosen soalDosen = noteDataSnapshot.getValue(SoalDosen.class);
                    soalDosen.setKey(noteDataSnapshot.getKey());

                    daftarSoal.add(soalDosen);
                }
                adapter = new SoalDosenAdapter(daftarSoal, SoalActivity.this);
                rvSoal.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }
}
