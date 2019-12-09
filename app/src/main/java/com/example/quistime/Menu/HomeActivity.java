package com.example.quistime.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quistime.R;

public class HomeActivity extends AppCompatActivity {
    Button btnMhs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnMhs = findViewById(R.id.btnMhs);
        btnMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,MhsLoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void clickDosen(View view) {
        Intent intent = new Intent(this,LoginDosenActivity.class);
        startActivity(intent);
    }
}
