package com.example.quistime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.quistime.Menu.HomeActivity;
import com.example.quistime.Models.Login;
import com.example.quistime.Models.Matkul;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.quistime.Adapter.MatkulDosenAdapter.MATKUL;

public class MainActivity extends AppCompatActivity {
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                finish();
            }
        },1000);
    }
}
