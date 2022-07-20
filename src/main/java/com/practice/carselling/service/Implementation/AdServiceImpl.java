package com.practice.carselling.service.Implementation;

import com.practice.carselling.entity.Ad;
import com.practice.carselling.repository.AdRepository;
import com.practice.carselling.service.AdService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository repository;

    @Override
    public Ad readById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Ad not found by id: " + id));
    }

    @Override
    public List<Ad> list(int page, int size) {
        return repository.findAll(PageRequest.of(page, size)).toList();
    }

    @Override
    public List<Ad> readAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Ad save(Ad entity) {
        return repository.save(entity);
    }
}
