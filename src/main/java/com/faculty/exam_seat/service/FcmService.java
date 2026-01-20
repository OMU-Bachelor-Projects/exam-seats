package com.faculty.exam_seat.service;

import com.faculty.exam_seat.model.NotificationMessage;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

@Service
public class FcmService {

    // Builder Pattern ile oluşturulan mesajı alıp Google'a gönderir
    public void sendNotification(NotificationMessage notificationMessage) {

        // Burada Google'ın kendi Builder yapısın   ı kullanıyoruz
        Message message = Message.builder()
                .setToken(notificationMessage.getToken())
                .setNotification(Notification.builder()
                        .setTitle(notificationMessage.getTitle())
                        .setBody(notificationMessage.getBody())
                        .build())
                .build();

        try {
            // Şimdilik gerçek gönderim yapmasak bile kodun çalışması için bu yeterli
            // (Gerçek gönderim için Firebase ayar dosyası gerekir)
            System.out.println("Bildirim Hazırlandı: " + notificationMessage.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}