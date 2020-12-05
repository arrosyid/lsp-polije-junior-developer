package com.example.juniordeveloper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.juniordeveloper.database.SQLiteHelper;

public class MainActivity extends AppCompatActivity {
    SQLiteHelper sqLiteHelper;
    EditText editUname, editPassword;
    Button BtnLogin, BtnDaftar;
    String dataLogin;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqLiteHelper = new SQLiteHelper(this);

        editUname = findViewById(R.id.username);
        editPassword = findViewById(R.id.Password);
        BtnLogin = findViewById(R.id.Intent_BtnLogin);
        BtnDaftar = findViewById(R.id.Intent_BtnDaftar);
        sharedPreferences = MainActivity.this.getSharedPreferences("Remember", MODE_PRIVATE);
        dataLogin = sharedPreferences.getString("login", "");
        if (dataLogin.isEmpty()) {

        } else {
            startActivity(new Intent(MainActivity.this, MapsActivity.class));
            finish();
        }
        Log.e("data login", dataLogin);
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = editUname.getText().toString();
                String Password = editPassword.getText().toString();
                if (Username.isEmpty()) {
                    editUname.setError("masukkan username");
                    return;
                } else {
                    editUname.setError(null);
                }

                if (Password.isEmpty()) {
                    editPassword.setError("masukkan password");
                    return;
                } else {
                    editPassword.setError(null);
                }

                if (sqLiteHelper.getSingleUser(Username, Password)) {
                    Toast.makeText(MainActivity.this, "selamat datang" + Username, Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("login", Username);
                    editor.commit();
                    startActivity(new Intent(MainActivity.this, MapsActivity.class));
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Username Atau Password anda salah" + Username, Toast.LENGTH_LONG).show();
                    editUname.setText("");
                    editPassword.setText("");
                    editUname.requestFocus();
                }
            }
        });

    }

    public void registerAction(View view) {
        startActivity(new Intent(MainActivity.this, DaftarActivity.class));
        finish();
    }

}