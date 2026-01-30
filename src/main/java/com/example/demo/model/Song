package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private int duration; // seconds

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "singer_id")
    private Singer singer;
}
