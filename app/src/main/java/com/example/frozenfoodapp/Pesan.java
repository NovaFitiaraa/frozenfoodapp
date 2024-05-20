package com.example.frozenfoodapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pesan extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    public static final String URLTAMPIL = "http://192.168.18.47/frozenfoodapp/select.php";
    public static final String URLDELETE = "http://192.168.18.47/frozenfoodapp/delete.php";
    public static final String URLUBAH = "http://192.168.18.47/frozenfoodapp/edit.php";
    public static final String URLINSERT = "http://192.168.18.47/frozenfoodapp/insert.php";
    ListView list;
    AlertDialog.Builder dialog;
    SwipeRefreshLayout swipe;
    List<Data> itemList = new ArrayList<Data>();
    UserAdapter adapter;

    LayoutInflater inflater;
    View dialogView;
    EditText tid,tnama,tjenismakanan,tjumlah;
    String id, nama, jenismakanan, jumlah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan);

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        list = (ListView) findViewById(R.id.list);

        adapter = new UserAdapter(Pesan.this, itemList);
        list.setAdapter(adapter);

        swipe.setOnRefreshListener(this);

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(true);
                           itemList.clear();
                           adapter.notifyDataSetChanged();
                           callVolley();
                       }
                   }
        );
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String idx = itemList.get(position).getId();
                final CharSequence[] dialogitem = {"Edit", "Delete"};
                dialog = new AlertDialog.Builder(Pesan.this);
                // dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                ubah(idx);
                                break;
                            case 1:
                                hapus(idx);
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });



    }
    @Override
    public void onRefresh() {
        //  itemList.clear();
        //  adapter.notifyDataSetChanged();
        callVolley();
    }
    private void callVolley() {
        itemList.clear();
        adapter.notifyDataSetChanged();
        swipe.setRefreshing(true);

        // membuat request JSON
        JsonArrayRequest jArr = new JsonArrayRequest(URLTAMPIL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);

                        Data item = new Data();

                        item.setId(obj.getString("id"));
                        item.setNama(obj.getString("nama"));
                        item.setJenismakanan(obj.getString("jenismakanan"));
                        item.setJumlah(obj.getString("jumlah"));

                        // menambah item ke array
                        itemList.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                // notifikasi adanya perubahan data pada adapter
                adapter.notifyDataSetChanged();

                swipe.setRefreshing(false);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Pesan.this, "gagal koneksi ke server, cek setingan koneksi anda", Toast.LENGTH_LONG).show();
                swipe.setRefreshing(false);
            }
        });

        // menambah request ke request queue
        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mRequestQueue.add(jArr);

    }
    private void ubah(String id){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLUBAH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);

                            String idx = jObj.getString("id");
                            String namax = jObj.getString("nama");
                            String jenismakananx = jObj.getString("jenismakanan");
                            String jumlahx = jObj.getString("jumlah");

                            DialogForm(idx, namax, jenismakananx, jumlahx, "UPDATE");

                            adapter.notifyDataSetChanged();

                        }catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Pesan.this, "gagal koneksi ke server, cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("id", id );
                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }
    private void hapus(String id){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLDELETE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callVolley();
                        Toast.makeText(Pesan.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Pesan.this, "gagal koneksi ke server, cek setingan koneksi anda", Toast.LENGTH_LONG).show();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("id", id );
                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }
    void simpan(){


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLINSERT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callVolley();
                        Toast.makeText(Pesan.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Pesan.this, "gagal koneksi ke server, cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();

                if (id.isEmpty()) {
                    params.put("nama", nama);
                    params.put("jenismakanan", jenismakanan);
                    params.put("jumlah", jumlah);
                    return params;
                }else{
                    params.put("id", id);
                    params.put("nama", nama);
                    params.put("jenismakanan", jenismakanan);
                    params.put("jumlah", jumlah);
                    return params;
                }
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);


    }
    private void DialogForm(String idx, String namax, String jenismakananx, String jumlahx, String button) {
        dialog = new AlertDialog.Builder(Pesan.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.form_frozenfood, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setTitle("Edit");

        tid = (EditText) dialogView.findViewById(R.id.InId);
        tnama = (EditText) dialogView.findViewById(R.id.InNama);
        tjenismakanan = (EditText) dialogView.findViewById(R.id.InJenismakanan);
        tjumlah = (EditText) dialogView.findViewById(R.id.InJumlah);

        if (!idx.isEmpty()) {
            tid.setText(idx);
            tnama.setText(namax);
            tjenismakanan.setText(jenismakananx);
            tjumlah.setText(jumlahx);
        } else {
            kosong();
        }

        dialog.setPositiveButton(button, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                id = tid.getText().toString();
                nama = tnama.getText().toString();
                jenismakanan = tjenismakanan.getText().toString();
                jumlah = tjumlah.getText().toString();
                simpan();

                dialog.dismiss();
            }
        });

        dialog.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                kosong();
            }
        });

        dialog.show();

    }
    private void kosong() {
        tid.setText(null);
        tnama.setText(null);
        tjenismakanan.setText(null);
        tjumlah.setText(null);
    }


}