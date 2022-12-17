package com.staxrt.tutorial.repository;

import com.staxrt.tutorial.model.Rent;
import com.staxrt.tutorial.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findAllByInvoiceDateBetween(Date invoiceDateStart, Date invoiceDateEnd);

    default void newSale(Long rentId, Rent rent) {
        final BigDecimal dayPrice = new BigDecimal("100");
        final BigDecimal dayInMs = new BigDecimal("86400000");
        BigDecimal price = BigDecimal.valueOf((rent.getEndRental().getTime() - rent.getStartRental().getTime())).divide(dayInMs, RoundingMode.HALF_UP).multiply(dayPrice);
        Sale newSale = new Sale();
        newSale.setPrice(price);
        newSale.setRentId(rentId);
        SaleRepository.this.save(newSale);
    }

}
