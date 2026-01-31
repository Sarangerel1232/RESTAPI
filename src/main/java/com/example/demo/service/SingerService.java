package com.example.demo.service;

import com.example.demo.model.Singer;
import com.example.demo.repository.SingerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SingerService {
    private final SingerRepository singerRepository;

    public SingerService(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }

    public Singer createSinger(Singer singer) {
        return singerRepository.save(singer);
    }

    public List<Singer> getAllSingers() {
        return singerRepository.findAll();
    }

    public Optional<Singer> getSingerById(Long id) {
        return singerRepository.findById(id);
    }

    public Optional<Singer> updateSinger(Long id, Singer updated) {
        return singerRepository.findById(id).map(singer -> {
            singer.setName(updated.getName());
            singer.setCountry(updated.getCountry());
            return singerRepository.save(singer);
        });
    }

    public boolean deleteSinger(Long id) {
        return singerRepository.findById(id).map(singer -> {
            singerRepository.delete(singer);
            return true;
        }).orElse(false);
    }
}
