package com.example.quistime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.quistime.Models.Login;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.quistime.Menu.LoginDosenActivity.LOGIN;

public class MainActivity extends AppCompatActivity {
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.textView);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Login l = extras.getParcelable(LOGIN);
            txt.setText(l.getEmail() +" "+ l.getPassword());
        }
    }
}
