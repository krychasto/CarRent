package com.staxrt.tutorial.repository;

import com.staxrt.tutorial.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    default void newSale(BigDecimal price, Long rentId) {
        Sale newSale = new Sale();
        newSale.setPrice(price);
        newSale.setRentId(rentId);
        SaleRepository.this.save(newSale);
    }
}
