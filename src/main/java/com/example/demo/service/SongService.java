package com.example.demo.service;

import com.example.demo.model.Album;
import com.example.demo.model.Song;
import com.example.demo.model.Singer;
import com.example.demo.repository.AlbumRepository;
import com.example.demo.repository.SingerRepository;
import com.example.demo.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final SingerRepository singerRepository;

    public SongService(SongRepository songRepository,
                       AlbumRepository albumRepository,
                       SingerRepository singerRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
        this.singerRepository = singerRepository;
    }

    public Optional<Song> createSong(Song song, Long albumId, Long singerId) {
        Optional<Album> albumOpt = albumRepository.findById(albumId);
        Optional<Singer> singerOpt = singerRepository.findById(singerId);

        if (albumOpt.isEmpty() || singerOpt.isEmpty()) {
            return Optional.empty();
        }

        song.setAlbum(albumOpt.get());
        song.setSinger(singerOpt.get());
        return Optional.of(songRepository.save(song));
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }
    public Optional<Song> getSongById(Long id) {
    return songRepository.findById(id);
}

    public Optional<Song> updateSong(Long id, Song updated) {
    return songRepository.findById(id).map(song -> {
        song.setTitle(updated.getTitle());
        song.setDuration(updated.getDuration());
        return songRepository.save(song);
    });
}

public boolean deleteSong(Long id) {
    return songRepository.findById(id).map(song -> {
        songRepository.delete(song);
        return true;
    }).orElse(false);
}

}
