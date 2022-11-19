package com.staxrt.tutorial.repository;

import com.staxrt.tutorial.model.Rent;
import com.staxrt.tutorial.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    default void newSale(Long rentId, Rent rent) {
        final BigDecimal DayPrice = new BigDecimal("100");
        final BigDecimal DayInMs = new BigDecimal("86400000");
        BigDecimal price = BigDecimal.valueOf((rent.getEndRental().getTime() - rent.getStartRental().getTime())).divide(DayInMs, RoundingMode.HALF_UP).multiply(DayPrice);
        Sale newSale = new Sale();
        newSale.setPrice(price);
        newSale.setRentId(rentId);
        SaleRepository.this.save(newSale);
    }
}
