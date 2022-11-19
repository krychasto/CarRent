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
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleService {

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
            saleRepository.newSale(rentId, rent);
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
