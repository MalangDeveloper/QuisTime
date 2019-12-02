package com.example.quistime.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quistime.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginDosenActivity extends AppCompatActivity {
    private EditText txtEmail, txtPassword;
    private Button btnLogin, btnTambah;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_dosen);

        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnTambah = findViewById(R.id.btnTambahSoal);
        auth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginDosenActivity.this,SigninActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Login(){
        final String email = txtEmail.getText().toString().trim();
        final String password = txtPassword.getText().toString().trim();

        if (email.isEmpty()){
            txtEmail.setError("Isikan Email");
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            txtEmail.setError("Masukkan Email Valid");
        }else if (password.isEmpty()){
            txtPassword.setError("Isikan Password");
        }else if (password.length() < 6){
            txtPassword.setError("Password Minimal 6 Karakter");
        }else {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginDosenActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Intent intent = new Intent(LoginDosenActivity.this, MatkulActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(LoginDosenActivity.this, "Login Gagal "+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
