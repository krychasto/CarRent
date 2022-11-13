package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.model.Car;
import com.staxrt.tutorial.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("car")
public class CarController {

    private final CarService carService;

    @GetMapping()
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping()
    public void createCar(@Valid @RequestBody Car car) {
        carService.createCar(car);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Car> deleteUser(@PathVariable(value = "id") Long carId) {
        return carService.deleteCar(carId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> findCarById(@PathVariable(value = "id") Long carId) {
        return ResponseEntity.ok(carService.findCarById(carId));
    }
}
