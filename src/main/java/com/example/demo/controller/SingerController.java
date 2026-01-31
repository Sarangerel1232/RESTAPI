package com.example.demo.controller;

import com.example.demo.model.Singer;
import com.example.demo.service.SingerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/singers")
public class SingerController {
    private final SingerService singerService;

    public SingerController(SingerService singerService) {
        this.singerService = singerService;
    }

    @PostMapping
    public Singer createSinger(@RequestBody Singer singer) {
        return singerService.createSinger(singer);
    }

    @GetMapping
    public List<Singer> getAllSingers() {
        return singerService.getAllSingers();
    }

    @GetMapping("/{id}")
    public Singer getSingerById(@PathVariable Long id) {
        return singerService.getSingerById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Singer not found"));
    }

    @PutMapping("/{id}")
    public Singer updateSinger(@PathVariable Long id, @RequestBody Singer singer) {
        return singerService.updateSinger(id, singer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Singer not found"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSinger(@PathVariable Long id) {
        if (!singerService.deleteSinger(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Singer not found");
        }
    }
}
