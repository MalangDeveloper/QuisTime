package com.example.quistime.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quistime.Adapter.SoalDosenAdapter;
import com.example.quistime.Models.Matkul;
import com.example.quistime.Models.SoalDosen;
import com.example.quistime.R;
import com.google.android.gms.tasks.OnSuccessListener;
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
    Matkul m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);

        btnTambah = findViewById(R.id.btnTambahSoal);
        rvSoal= findViewById(R.id.rvSoal);
        layoutManager = new LinearLayoutManager(this);
        rvSoal.setLayoutManager(layoutManager);

        Bundle extras = getIntent().getExtras();
        m = extras.getParcelable(MATKUL);

        final String key = m.getKey();
        final String matkul = m.getMatkul();

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Matkul m = new Matkul(matkul, key);
                Intent intent = new Intent(SoalActivity.this,TambahSoalActivity.class);
                intent.putExtra(MATKUL, m);
                startActivity(intent);
            }
        });

        database = FirebaseDatabase.getInstance().getReference();
        database.child("Matkul").child(m.getKey()).child("Soal").addValueEventListener(new ValueEventListener() {
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
    public void onDeleteData(final SoalDosen soalDosen, final int position) {
        if(database!=null){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Konfirmasi Hapus");
            alert.setMessage("Apakah ingin Menghapus Data?");
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    database.child("Matkul").child(m.getKey()).child("Soal").child(soalDosen.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(SoalActivity.this,"success delete", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            alert.create().show();
        }
    }

    public void onUpdateData(final SoalDosen soal, final Context context){
        database.child("Matkul").child(m.getKey()).child("Soal").child(soal.getKey()).setValue(soal).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(context, "SoalDosen Updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
