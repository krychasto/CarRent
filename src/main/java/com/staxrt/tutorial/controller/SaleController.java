package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.model.Sale;
import com.staxrt.tutorial.services.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("sale")
public class SaleController {

    private final SaleService saleService;

    @GetMapping()
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    @PostMapping("/{rentid}")
    public void createSale(@PathVariable(value = "rentid") Long rentId) throws Exception {
        saleService.createSale(rentId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSale(@PathVariable(value = "id") Long saleId) {
        saleService.deleteSale(saleId);
        return ResponseEntity.ok().build();
    }
}
