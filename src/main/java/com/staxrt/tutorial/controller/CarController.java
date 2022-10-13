package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.model.Car;
import com.staxrt.tutorial.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v2")
public class CarController {
    @Autowired
    CarService carService;

    @GetMapping("/cars")
    public List<Car> getAllCars(){
        return carService.getAllCars();
    }

    @PostMapping("/cars")
    public void createCar(@Valid @RequestBody Car car){
        carService.createCar(car);
    }

    @DeleteMapping("/cars/{id}")
    public void deleteUser(@PathVariable(value="id") Long carId) throws Exception{
        carService.deleteCar(carId);
    }
    @GetMapping("/cars/{id}")
    public Car findCarById(@PathVariable(value = "id") Long carId) throws Exception {
        return carService.findCarById(carId);
    }
}
