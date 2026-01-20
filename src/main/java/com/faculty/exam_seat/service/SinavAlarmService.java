package com.faculty.exam_seat.service;

import com.faculty.exam_seat.model.Sinav;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class SinavAlarmService {

    private String sonDurumMesaji = "Sistem Beklemede (Cron Job Aktif)...";
    private List<Sinav> sinavListesi = new ArrayList<>();

    // Dosya yüklendiğinde listeyi buraya alıyoruz
    public void sinavListesiEkle(List<Sinav> yeniSinavlar) {
        this.sinavListesi = yeniSinavlar;
        this.sonDurumMesaji = "✅ " + yeniSinavlar.size() + " sınav takibe alındı. Cron Job bekleniyor.";
    }

    // CRON JOB: Her dakikanın 0. saniyesinde çalışır
    @Scheduled(cron = "0 * * * * *")
    public void zamaniKontrolEt() {
        if (sinavListesi.isEmpty()) return;

        LocalDateTime suAn = LocalDateTime.now();
        boolean alarmCaldi = false;

        for (Sinav sinav : sinavListesi) {
            // Sınava kaç dakika kaldığını hesapla
            long dakikaFarki = ChronoUnit.MINUTES.between(suAn, sinav.getTarih());

            // --- KRİTİK DÜZELTME ---
            // Sadece "== 60" dersek uygulama geç açılınca kaçırabiliyor.
            // "59 ile 61 arası" diyerek 2 dakikalık bir güvenlik payı bırakıyoruz.
            if (dakikaFarki >= 59 && dakikaFarki <= 61) {
                this.sonDurumMesaji = "🚨 DİKKAT! " + sinav.getDersAdi() + " sınavına 1 SAAT kaldı! (" + sinav.getSiniflar() + ")";
                alarmCaldi = true;
                System.out.println(this.sonDurumMesaji);
            }
        }
    }

    public String getSonDurumMesaji() {
        return sonDurumMesaji;
    }
}