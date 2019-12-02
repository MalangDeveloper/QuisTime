package com.example.quistime.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.quistime.Adapter.MatkulDosenAdapter;
import com.example.quistime.Models.Login;
import com.example.quistime.Models.Matkul;
import com.example.quistime.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MatkulActivity extends AppCompatActivity {
    private Button buttonTambah;
    private RecyclerView rvMatkul;
    private ArrayList<Matkul> daftarMatkul;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matkul);

        rvMatkul = findViewById(R.id.rvMatkul);
        layoutManager = new LinearLayoutManager(this);
        rvMatkul.setLayoutManager(layoutManager);

        buttonTambah = findViewById(R.id.btnTambahMatkul);
        buttonTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MatkulActivity.this,TambahMatkulActivity.class);
                startActivity(intent);
            }
        });


        database = FirebaseDatabase.getInstance().getReference();
        database.child("Matkul").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                daftarMatkul = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Matkul matkul = noteDataSnapshot.getValue(Matkul.class);
                    matkul.setKey(noteDataSnapshot.getKey());

                    daftarMatkul.add(matkul);
                }
                adapter = new MatkulDosenAdapter(daftarMatkul, MatkulActivity.this);
                rvMatkul.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }
}
