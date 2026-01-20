package com.faculty.exam_seat.controller;

import com.faculty.exam_seat.service.SinavAlarmService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AlarmController {

    private final SinavAlarmService alarmService;

    public AlarmController(SinavAlarmService alarmService) {
        this.alarmService = alarmService;
    }

    @GetMapping("/alarm-durumu")
    public String durumiGetir() {
        return alarmService.getSonDurumMesaji();
    }
}