package com.faculty.exam_seat.model;

import java.time.LocalDateTime;

public class Sinav {
    private String dersAdi;
    private LocalDateTime tarih;
    private String siniflar;

    public Sinav(String dersAdi, LocalDateTime tarih, String siniflar) {
        this.dersAdi = dersAdi;
        this.tarih = tarih;
        this.siniflar = siniflar;
    }

    public String getDersAdi() { return dersAdi; }
    public LocalDateTime getTarih() { return tarih; }
    public String getSiniflar() { return siniflar; }
}