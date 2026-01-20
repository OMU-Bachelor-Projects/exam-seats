package com.faculty.exam_seat;

import com.faculty.exam_seat.model.NotificationMessage;
import com.faculty.exam_seat.service.FcmService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  // <-- İŞTE BU SATIRI EKLEMEN YETERLİ
public class ExamSeatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamSeatApplication.class, args);
	}

	// Bu metod program ayağa kalktığında otomatik çalışır
	@Bean
	public CommandLineRunner demo(FcmService fcmService) {
		return (args) -> {
			// Builder Pattern Kullanımı
			NotificationMessage msg = new NotificationMessage.Builder("Sınav Duyurusu", "Token_123")
					.body("Yarın saat 10:00'da sınavınız var.")
					.image("sinav_krokisi.jpg")
					.build();

			// Servisi Test Etme
			fcmService.sendNotification(msg);
		};
	}
}