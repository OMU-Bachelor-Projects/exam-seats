package com.faculty.exam_seat.controller;

import com.faculty.exam_seat.model.Sinav;
import com.faculty.exam_seat.service.SinavAlarmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FileUploadController {

    private final SinavAlarmService alarmService;

    public FileUploadController(SinavAlarmService alarmService) {
        this.alarmService = alarmService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) return ResponseEntity.badRequest().body("Dosya seçilmedi.");

        String fileName = file.getOriginalFilename();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));

            // --- .exam veya .txt Dosyası ---
            if (fileName != null && (fileName.endsWith(".exam") || fileName.endsWith(".txt"))) {
                List<String> satirlar = new ArrayList<>();
                String line;
                while ((line = reader.readLine()) != null) {
                    if(!line.trim().isEmpty()) satirlar.add(line.trim());
                }

                List<Sinav> sinavlar = new ArrayList<>();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

                for (int i = 0; i < satirlar.size(); i += 3) {
                    if (i + 2 < satirlar.size()) {
                        String dersAdi = satirlar.get(i);
                        String tarihStr = satirlar.get(i+1);
                        String siniflar = satirlar.get(i+2);

                        try {
                            LocalDateTime tarih = LocalDateTime.parse(tarihStr, formatter);
                            sinavlar.add(new Sinav(dersAdi, tarih, siniflar));
                        } catch (Exception e) {
                            System.out.println("Tarih hatası: " + tarihStr);
                        }
                    }
                }

                alarmService.sinavListesiEkle(sinavlar);
                return ResponseEntity.ok("✅ Sınav Programı Okundu! " + sinavlar.size() + " ders için alarm kuruldu.");
            }
            // --- .csv Dosyası ---
            else if (fileName != null && fileName.endsWith(".csv")) {
                return ResponseEntity.ok("✅ Öğrenci listesi yüklendi.");
            }

            return ResponseEntity.badRequest().body("Lütfen .csv veya .exam dosyası yükleyin.");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Hata: " + e.getMessage());
        }
    }
}