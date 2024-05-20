package com.example.frozenfoodapp;

public class Data {
    private String id;
    private String nama;
    private String jenismakanan;

    public Data() {
    }

    public Data(String id, String nama, String jenismakanan, String jumlah) {
        this.id = id;
        this.nama = nama;
        this.jenismakanan = jenismakanan;
        this.jumlah = jumlah;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenismakanan() {
        return jenismakanan;
    }

    public void setJenismakanan(String jenismakanan) {
        this.jenismakanan = jenismakanan;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    private String jumlah;


}
