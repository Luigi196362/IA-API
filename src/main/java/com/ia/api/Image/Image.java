package com.ia.api.Image;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String classification;

    private String contentType;

    @Lob
    @Column(length = 1000000) // Adjust size as needed
    private byte[] data;

    @jakarta.persistence.ManyToOne
    @jakarta.persistence.JoinColumn(name = "user_id")
    private com.ia.api.User.User user;
}
