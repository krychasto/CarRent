package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.model.Sale;
import com.staxrt.tutorial.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/sale")
public class SaleController {

    @Autowired
    SaleService saleService;

    @GetMapping("/sales")
    public List<Sale> getAllSales(){return saleService.getAllSales();}

    @PostMapping("/rent/{rentid}")
    public void createSale(@PathVariable(value="rentid") Long rentId) throws Exception{
        saleService.createSale(rentId);
    }
}
