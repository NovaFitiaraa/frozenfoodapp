package com.example.frozenfoodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    CardView cvBeli,cvPesan,cvMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cvBeli = (CardView) findViewById(R.id.cvBeli);
        cvPesan = (CardView) findViewById(R.id.cvPesan);
        cvMenu = (CardView) findViewById(R.id.cvMenu);

        cvBeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Beli.class));
            }
        });

        cvPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Pesan.class));
            }
        });

        cvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Menu.class));
            }
        });
    }
}