package com.example.quistime.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quistime.Adapter.NilaiMhsAdapter;
import com.example.quistime.Models.Nilai;
import com.example.quistime.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListNilaiActivity extends AppCompatActivity {
    EditText txtCariNIM;
    Button btnCari;
    RecyclerView rvListNilai;
    private ArrayList<Nilai> daftarNilai;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nilai);

        txtCariNIM = findViewById(R.id.txtCariNIM);
        btnCari = findViewById(R.id.btnCari);
        rvListNilai = findViewById(R.id.rvListNilai);

        layoutManager = new LinearLayoutManager(this);
        rvListNilai.setLayoutManager(layoutManager);

        database = FirebaseDatabase.getInstance().getReference();
        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NIM = txtCariNIM.getText().toString().trim();
                if(NIM.isEmpty()){
                    txtCariNIM.setError("Masukkan NIM Terlebih Dahulu");
                }else if(NIM.length() == 9) {
                    txtCariNIM.setError("NIM terdiri dari 10 Karakter");
                }else {
                    database.child("Nilai").child(NIM).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            daftarNilai = new ArrayList<>();
                            for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                                Nilai nilai = noteDataSnapshot.getValue(Nilai.class);
                                nilai.setKey(noteDataSnapshot.getKey());

                                daftarNilai.add(nilai);
                            }
                            adapter = new NilaiMhsAdapter(daftarNilai, ListNilaiActivity.this);
                            rvListNilai.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
                        }
                    });
                }
            }
        });
    }
}
