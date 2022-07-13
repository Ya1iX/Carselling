package com.practice.carselling.service.Implementation;

import com.practice.carselling.entity.Car;
import com.practice.carselling.repository.CarRepository;
import com.practice.carselling.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository repository;

    @Override
    public Car readById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Car not found by id: " + id));
    }

    @Override
    public List<Car> list(int page, int size) {
        return repository.findAll(PageRequest.of(page, size)).toList();
    }

    @Override
    public List<Car> readAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void save(Car entity) {
        repository.save(entity);
    }
}
