package com.example.frozenfoodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Beli extends AppCompatActivity {

    public static final String url = "http://192.168.18.47/frozenfoodapp/insert.php";
    EditText jvnama,jvjenismakanan,jvjumlah;
    Button jvbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beli);

        jvnama = (EditText) findViewById(R.id.namaA);
        jvjenismakanan = (EditText) findViewById(R.id.jenismakananA);
        jvjumlah = (EditText) findViewById(R.id.jumlahA);
        jvbutton = (Button) findViewById(R.id.buttonsubmit);

        jvbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputData();
            }
        });
    }

    void inputData() {
        String nama = jvnama.getText().toString();
        String jenismakanan = jvjenismakanan.getText().toString();
        String jumlah = jvjumlah.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(getApplicationContext(), "Data berhasil di kirim",
                                Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Data gagal",
                        Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("nama",nama);
                params.put("jenismakanan",jenismakanan);
                params.put("jumlah",jumlah);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(Beli.this);
        queue.add(stringRequest);
    }
}