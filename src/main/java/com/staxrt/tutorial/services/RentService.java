package com.staxrt.tutorial.services;


import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.Rent;
import com.staxrt.tutorial.repository.CarRepository;
import com.staxrt.tutorial.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final UserRepository userRepository;

    private final CarRepository carRepository;
    public List<Rent> getAllRents(){
        String sql = "SELECT * FROM rents";
        return (List<Rent>) jdbcTemplate.query(sql, new BeanPropertyRowMapper(Rent.class));
    }
    @Transactional
    public void createRent(Rent rent) throws Exception{
        String sql = "INSERT INTO rents (car_id,user_id,start_rental,end_rental) VALUES (?,?,?,?)";
        if(!userRepository.findById(rent.getUserId()).isPresent()) {
            throw new ResourceNotFoundException("This user doesn't exists in database");
        }else if(!carRepository.findById(rent.getCarId()).isPresent()){
            throw new ResourceNotFoundException("This car doesn't exists in database");
        } else {
            jdbcTemplate.update(sql, rent.getCarId(), rent.getUserId(), rent.getStartRental(), rent.getEndRental());
        }
    }
}
