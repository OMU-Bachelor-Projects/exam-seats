package com.faculty.exam_seat.model;

public class NotificationMessage {
    private String title;
    private String body;
    private String image;
    private String token;

    // Private Constructor (Sadece Builder kullanabilsin diye)
    private NotificationMessage(Builder builder) {
        this.title = builder.title;
        this.body = builder.body;
        this.image = builder.image;
        this.token = builder.token;
    }

    // Getter Metotları
    public String getTitle() { return title; }
    public String getBody() { return body; }
    public String getToken() { return token; }

    // --- BUILDER PATTERN BURADA BAŞLIYOR ---
    public static class Builder {
        private String title;
        private String body;
        private String image;
        private String token;

        // Zorunlu alanlar
        public Builder(String title, String token) {
            this.title = title;
            this.token = token;
        }

        // Seçime bağlı (zincirleme) metotlar
        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder image(String image) {
            this.image = image;
            return this;
        }

        // Nesneyi üreten son komut
        public NotificationMessage build() {
            return new NotificationMessage(this);
        }
    }
}