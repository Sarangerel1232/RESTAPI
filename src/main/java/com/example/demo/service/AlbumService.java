package com.example.demo.service;

import com.example.demo.model.Album;
import com.example.demo.repository.AlbumRepository;
import com.example.demo.repository.SingerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final SingerRepository singerRepository;

    public AlbumService(AlbumRepository albumRepository, SingerRepository singerRepository) {
        this.albumRepository = albumRepository;
        this.singerRepository = singerRepository;
    }

    public Optional<Album> createAlbum(Album album, Long singerId) {
        return singerRepository.findById(singerId).map(singer -> {
            album.setSinger(singer);
            return albumRepository.save(album);
        });
    }

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    public Optional<Album> getAlbumById(Long id) {
        return albumRepository.findById(id);
    }
    public Optional<Album> updateAlbum(Long id, Album updated) {
    return albumRepository.findById(id).map(album -> {
        album.setTitle(updated.getTitle());
        album.setReleaseYear(updated.getReleaseYear());
        return albumRepository.save(album);
    });
}
public boolean deleteAlbum(Long id) {
    return albumRepository.findById(id).map(album -> {
        albumRepository.delete(album);
        return true;
    }).orElse(false);
}


}
