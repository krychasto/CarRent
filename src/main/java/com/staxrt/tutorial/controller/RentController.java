package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.model.Rent;
import com.staxrt.tutorial.services.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("rent")
public class RentController {
    private final RentService rentService;

    @GetMapping()
    public List<Rent> getAllRents() {
        return rentService.getAllRents();
    }

    @PostMapping()
    public void createRent(@Valid @RequestBody Rent rent) throws Exception {
        rentService.createRent(rent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRent(@PathVariable(value = "id") Long rentId) {
        return rentService.deleteRent(rentId);
    }
}
