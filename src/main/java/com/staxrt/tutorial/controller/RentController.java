package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.model.Rent;
import com.staxrt.tutorial.services.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/rent")
public class RentController {
    @Autowired
    RentService rentService;

    @GetMapping("/rents")
    public List<Rent> getAllRents(){
        return rentService.getAllRents();
    }

    @PostMapping("/rents")
    public void createRent(@Valid @RequestBody Rent rent) throws Exception{
        rentService.createRent(rent);
    }
}
