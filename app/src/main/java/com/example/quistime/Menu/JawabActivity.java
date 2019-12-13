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
    TextView timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jawab);

        timer = findViewById(R.id.txtTime);
        rvJawab= findViewById(R.id.rvJawab);
        layoutManager = new LinearLayoutManager(this);
        rvJawab.setLayoutManager(layoutManager);

        Bundle extras = getIntent().getExtras();
        Mahasiswa mhs = extras.getParcelable(MHS);
        nama = mhs.getNama();
        NIM = mhs.getNim();
        kelas = mhs.getKelas();
        token = mhs.getToken();

//        reverseTimer(10);

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
                Toast.makeText(JawabActivity.this, "Nilai Berhasil Disimpan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Kembali ke Menu Awal ?");
        alert.setIcon(R.drawable.quistimepng);
        alert.setMessage("Semua jawaban yang diisi tidak akan disimpan sebelim menyelesaikan Quis ini");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(JawabActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
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

    public void reverseTimer(int Seconds){
        new CountDownTimer(Seconds* 1000+1000, 1000) {
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                timer.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
            }
            public void onFinish() {
                timer.setText("Done");
            }
        }.start();
    }
}
