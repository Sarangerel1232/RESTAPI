package com.example.demo.controller;

import com.example.demo.model.Album;
import com.example.demo.service.AlbumService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping("/{singerId}")
    public Album createAlbum(@RequestBody Album album, @PathVariable Long singerId) {
        return albumService.createAlbum(album, singerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Singer not found"));
    }

    @GetMapping
    public List<Album> getAllAlbums() {
        return albumService.getAllAlbums();
    }

    @GetMapping("/{id}")
    public Album getAlbumById(@PathVariable Long id) {
        return albumService.getAlbumById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Album not found"));
    }
    @PutMapping("/{id}")
public Album updateAlbum(@PathVariable Long id, @RequestBody Album updated) {
    return albumService.updateAlbum(id, updated)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Album not found"));
    }
    @DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void deleteAlbum(@PathVariable Long id) {
    if (!albumService.deleteAlbum(id)) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Album not found");
    }
}


}
