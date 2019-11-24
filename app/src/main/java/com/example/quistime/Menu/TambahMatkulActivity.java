package com.example.quistime.Menu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quistime.Models.Login;
import com.example.quistime.Models.Matkul;
import com.example.quistime.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.quistime.Menu.LoginDosenActivity.LOGIN;

public class TambahMatkulActivity extends AppCompatActivity {
    private EditText txtNama;
    private EditText txtCode;
    private Button btnTambah;
    private DatabaseReference database;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_matkul);

        txtNama = findViewById(R.id.txtNama);
        txtCode = findViewById(R.id.txtCode);
        btnTambah = findViewById(R.id.btnTambahMatkul);
        database = FirebaseDatabase.getInstance().getReference();

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ambilData();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void ambilData(){
        final String matkul = txtNama.getText().toString().trim();
        final String code = txtCode.getText().toString().trim();

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String txtTanggal = sdf.format(cal.getTime());

        if (matkul.isEmpty()){
            txtNama.setError("Isikan Mata Kuliah");
        }else if (code.isEmpty()){
            txtCode.setError("Isikan Code daro Matkul");
        }else{
            tambahMatkul(new Matkul(matkul, code, txtTanggal));
        }
    }
    private void tambahMatkul(Matkul matkul){
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Login l = extras.getParcelable(LOGIN);
            database.child("Login").child(l.getPassword()).child("Matkul").child(matkul.getCode()).setValue(matkul).addOnSuccessListener(TambahMatkulActivity.this, new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(TambahMatkulActivity.this, "Berhasil Tambah Matkul", Toast.LENGTH_SHORT).show();
                }
            });
            Intent intent = new Intent(TambahMatkulActivity.this,MatkulActivity.class);
            l = new Login(l.getEmail(), l.getPassword());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(LOGIN, l);
            startActivity(intent);
            finish();
        }
    }
}
