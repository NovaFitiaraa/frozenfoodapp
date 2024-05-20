package com.example.frozenfoodapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    Activity activity;
    List<Data> items;
    private LayoutInflater inflater;

    public UserAdapter(Activity activity, List<Data> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) convertView = inflater.inflate(R.layout.list, null);

        TextView id = (TextView) convertView.findViewById(R.id.id);
        TextView nama = (TextView) convertView.findViewById(R.id.nama);
        TextView jenismakanan = (TextView) convertView.findViewById(R.id.jenismakanan);
        TextView jumlah = (TextView) convertView.findViewById(R.id.jumlah);

        Data data = items.get(position);

        id.setText(data.getId());
        nama.setText(data.getNama());
        jenismakanan.setText(data.getJenismakanan());
        jumlah.setText(data.getJumlah());

        return convertView;
    }
}
