package com.example.quistime.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quistime.Models.Matkul;
import com.example.quistime.R;

import static com.example.quistime.Adapter.MatkulDosenAdapter.MATKUL;

public class SoalActivity extends AppCompatActivity {
    Button btnTambah;
    RecyclerView rvSoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);

        btnTambah = findViewById(R.id.btnTambahSoal);
        rvSoal= findViewById(R.id.rvSoal);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle extras = getIntent().getExtras();
                    Matkul m = extras.getParcelable(MATKUL);
                Intent intent = new Intent(SoalActivity.this,TambahSoalActivity.class);
                startActivity(intent);
            }
        });
    }
}
