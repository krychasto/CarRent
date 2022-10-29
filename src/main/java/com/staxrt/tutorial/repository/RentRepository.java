package com.staxrt.tutorial.repository;

import com.staxrt.tutorial.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
    List<Rent> findByCarId(Long carId);
}
