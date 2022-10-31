package com.staxrt.tutorial.services;

import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.Rent;
import com.staxrt.tutorial.model.Sale;
import com.staxrt.tutorial.repository.RentRepository;
import com.staxrt.tutorial.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;
    private final RentRepository rentRepository;
    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    @Transactional
    public void createSale(Long rentId) throws Exception {
        BigDecimal DayPrice = new BigDecimal("100");
        BigDecimal m = new BigDecimal("86400000");
        String sql = "INSERT INTO sales (rent_id,price) VALUES (?,?)";
        if (!rentRepository.findById(rentId).isPresent()) {
            throw new ResourceNotFoundException("This rent doesn't exists");
        } else {
            Rent rent = rentRepository.findById(rentId).get();
            BigDecimal price = BigDecimal.valueOf((rent.getEndRental().getTime() - rent.getStartRental().getTime())).divide(m).multiply(DayPrice);
            jdbcTemplate.update(sql, rentId, price);
        }
    }

    @Transactional
    public ResponseEntity deleteSale(Long id) {
        Optional<Sale> sale = saleRepository.findById(id);
        if (sale.isPresent()) {
            saleRepository.delete(sale.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
