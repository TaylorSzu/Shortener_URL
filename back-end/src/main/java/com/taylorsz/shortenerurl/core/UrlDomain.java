package com.taylorsz.shortenerurl.core;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shortener_url")
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UrlDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String originalUrl;
    @Column(nullable = true)
    private String shortenerUrl;

    @PostPersist
    public void generateShortener(){
        if (this.shortenerUrl == null) {
            Base64 base64 = new Base64();
            String shortenerUrl = "https://shortenerurl-8bfw.onrender.com/url/" + base64.encode(this.id);
            this.shortenerUrl = shortenerUrl;
        }
    }
}
