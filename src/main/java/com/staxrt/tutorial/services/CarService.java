package com.staxrt.tutorial.services;

import com.staxrt.tutorial.model.Car;
import com.staxrt.tutorial.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public void createCar(Car car) {
        carRepository.save(car);
    }

    public ResponseEntity<Car> deleteCar(Long id) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent()) {
            carRepository.delete(car.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public Car findCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }
}
