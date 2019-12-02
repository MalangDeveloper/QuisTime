package com.example.quistime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

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

        txt = findViewById(R.id.textView);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Matkul m = extras.getParcelable(MATKUL);
            txt.setText(m.getCode() +" "+ m.getMatkul());
        }
    }
}
