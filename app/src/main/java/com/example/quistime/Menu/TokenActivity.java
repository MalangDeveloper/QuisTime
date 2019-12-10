package com.example.quistime.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quistime.Models.Mahasiswa;
import com.example.quistime.R;

import static com.example.quistime.Menu.MhsLoginActivity.MHS;

public class TokenActivity extends AppCompatActivity {
    TextView txttoken;
    Button btnMasuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token);

        txttoken = findViewById(R.id.txtToken);
        btnMasuk = findViewById(R.id.btnMasuk);

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle extras = getIntent().getExtras();
                Mahasiswa mhs = extras.getParcelable(MHS);
                mhs.setToken(txttoken.getText().toString());
                Intent intent = new Intent(TokenActivity.this, JawabActivity.class);
                intent.putExtra(MHS, mhs);
                startActivity(intent);
                finish();
            }
        });
    }
}
