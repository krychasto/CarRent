package com.staxrt.tutorial.services;

import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.Rent;
import com.staxrt.tutorial.model.Sale;
import com.staxrt.tutorial.repository.RentRepository;
import com.staxrt.tutorial.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final static BigDecimal DayPrice = new BigDecimal("100");
    private final static BigDecimal DayInMs = new BigDecimal("86400000");
    private final SaleRepository saleRepository;
    private final RentRepository rentRepository;

    @Transactional
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    @Transactional
    public void createSale(Long rentId) throws Exception {
        if (!rentRepository.findById(rentId).isPresent()) {
            throw new ResourceNotFoundException("This rent doesn't exists");
        } else {
            Rent rent = rentRepository.findById(rentId).get();
            BigDecimal price = BigDecimal.valueOf((rent.getEndRental().getTime() - rent.getStartRental().getTime())).divide(DayInMs, RoundingMode.HALF_UP).multiply(DayPrice);
            saleRepository.newSale(price, rentId);
        }
    }

    @Transactional
    public ResponseEntity<Sale> deleteSale(Long id) {
        Optional<Sale> sale = saleRepository.findById(id);
        if (sale.isPresent()) {
            saleRepository.delete(sale.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
