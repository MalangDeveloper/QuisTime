package com.example.quistime.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.quistime.R;

public class TokenActivity extends AppCompatActivity {
    TextView txttoken;
    Button btnMasuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token);

        txttoken = findViewById(R.id.txtToken);
        btnMasuk = findViewById(R.id.btnMasuk);


    }
}
