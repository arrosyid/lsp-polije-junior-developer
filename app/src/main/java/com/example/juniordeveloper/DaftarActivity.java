package com.example.juniordeveloper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.juniordeveloper.database.SQLiteHelper;
import com.example.juniordeveloper.model.ModelDaftar;

public class DaftarActivity extends AppCompatActivity {
    EditText editName, editEmail, editUname, editPassword;
    Button BtnClear, BtnDaftar;
    SharedPreferences sharedPreferences;
    String dataLogin;
    SQLiteHelper sqLiteHelper;
    ModelDaftar modelDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        editName = findViewById(R.id.daftarNama);
        editEmail = findViewById(R.id.daftarEmail);
        editUname = findViewById(R.id.daftarUsername);
        editPassword = findViewById(R.id.daftarPassword);

        BtnDaftar = findViewById(R.id.BtnSave);
        BtnClear = findViewById(R.id.BtnClear);

        sqLiteHelper = new SQLiteHelper(DaftarActivity.this);

        BtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editName.setText("");
                editEmail.setText("");
                editUname.setText("");
                editPassword.setText("");
                editName.requestFocus();
            }
        });

        BtnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = editName.getText().toString().trim();
                String email = editEmail.getText().toString().trim();
                String username = editUname.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                if (nama.isEmpty()) {
                    editName.setError("masukkan nama");
                    return;
                } else
                    editName.setError(null);
                if (email.isEmpty()) {
                    editEmail.setError("masukkan email");
                    return;
                } else
                    editEmail.setError(null);
                if (username.isEmpty()) {
                    editUname.setError("masukkan username");
                    return;
                } else
                    editUname.setError(null);
                if (password.isEmpty()) {
                    editPassword.setError("masukkan Password");
                    return;
                } else
                    editPassword.setError(null);
                if (sqLiteHelper.insertUser(new ModelDaftar(nama, email, username, password))) {
                    Toast.makeText(DaftarActivity.this, "berhasil daftar", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(DaftarActivity.this, MainActivity.class));
                    finish();
                } else
                    Toast.makeText(DaftarActivity.this, "Username telah digunakan", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(DaftarActivity.this, MainActivity.class));
        finish();
    }
}