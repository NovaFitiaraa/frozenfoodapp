package com.example.frozenfoodapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText username,password;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username =(EditText) findViewById(R.id.editTextTextEmailAddress1);
        password =(EditText) findViewById(R.id.editTextTextPassword2);
        btnLogin =(Button) findViewById(R.id.buttonlgn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usernameKey = username.getText().toString();
                String passwordKey = password.getText().toString();

                if (usernameKey.equals("nova") && passwordKey.equals("123")){
                    Toast.makeText(getApplicationContext(), "Login Berhasil",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this,Home.class);
                    Login.this.startActivity(intent);
                    finish();
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setMessage("Username Atau Password Salah")
                            .setNegativeButton("Retry",null).create().show();
                }
            }
        });

    }
}