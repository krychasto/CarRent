package com.staxrt.tutorial.services;

import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.Rent;
import com.staxrt.tutorial.repository.CarRepository;
import com.staxrt.tutorial.repository.RentRepository;
import com.staxrt.tutorial.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentService {
    private final UserRepository userRepository;
    private final RentRepository rentRepository;
    private final CarRepository carRepository;
    private final JdbcTemplate jdbcTemplate;

    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }

    @Transactional
    public void createRent(Rent rent) throws Exception {
        List<Rent> carRents = rentRepository.findByCarId(rent.getCarId());
        boolean isNotPossible = false;
        for (Rent carRent : carRents) {
            if (!((rent.getStartRental().before(carRent.getStartRental()) && rent.getEndRental().before(carRent.getStartRental())) || (rent.getStartRental().after(carRent.getEndRental()) && rent.getEndRental().after(carRent.getEndRental())))) {
                isNotPossible = true;
                break;
            }
        }
        if (!userRepository.findById(rent.getUserId()).isPresent()) {
            throw new ResourceNotFoundException("This user doesn't exists in database");
        } else if (!carRepository.findById(rent.getCarId()).isPresent()) {
            throw new ResourceNotFoundException("This car doesn't exists in database");
        } else if (isNotPossible) {
            throw new IllegalStateException("This car is already rented");
        } else {
            rentRepository.save(rent);
        }
    }

    @Transactional
    public ResponseEntity deleteRent(Long id) {
        Optional<Rent> rent = rentRepository.findById(id);
        if (rent.isPresent()) {
            rentRepository.delete(rent.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
