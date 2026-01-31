package com.example.demo.controller;

import com.example.demo.model.Song;
import com.example.demo.service.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping("/{albumId}/{singerId}")
    public Song createSong(@RequestBody Song song,
                           @PathVariable Long albumId,
                           @PathVariable Long singerId) {
        return songService.createSong(song, albumId, singerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Album or Singer not found"));
    }

    @GetMapping
    public List<Song> getAllSongs() {
        return songService.getAllSongs();
    }
    @GetMapping("/{id}")
public Song getSongById(@PathVariable Long id) {
    return songService.getSongById(id)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Song not found"));
}

    @PutMapping("/{id}")
public Song updateSong(@PathVariable Long id, @RequestBody Song updated) {
    return songService.updateSong(id, updated)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found"));
}

@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void deleteSong(@PathVariable Long id) {
    if (!songService.deleteSong(id)) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found");
    }
}

}
