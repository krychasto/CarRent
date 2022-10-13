package com.staxrt.tutorial.services;

import com.staxrt.tutorial.model.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<Car> getAllCars() {
        String sql = "SELECT * FROM cars";
        return (List<Car>) jdbcTemplate.query(sql,new BeanPropertyRowMapper(Car.class));
    }
    public void createCar(Car car){
        String sql = "INSERT INTO cars (car_brand,engine_capacity,vin) VALUES (?,?,?)";
        jdbcTemplate.update(sql,car.getCarBrand(),car.getEngineCapacity(),car.getVin());
    }
    public void deleteCar(Long id){
        String sql = "DELETE FROM cars WHERE id="+id;
        jdbcTemplate.execute(sql);
    }
    public Car findCarById(Long id){
        String sql = "SELECT * FROM cars WHERE id="+id;
        return (Car) jdbcTemplate.queryForObject(sql, new Object[]{}, new BeanPropertyRowMapper(Car.class));
    }
}
