package com.staxrt.tutorial.services;

import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.Rent;
import com.staxrt.tutorial.model.Sale;
import com.staxrt.tutorial.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.management.relation.RelationServiceNotRegisteredException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final RentRepository rentRepository;

    public List<Sale> getAllSales(){
        String sql = "SELECT * FROM sales";
        return (List<Sale>)  jdbcTemplate.query(sql, new BeanPropertyRowMapper(Sale.class));
    }
    public void createSale(Long rentId) throws Exception{
        Double oneDayPrice = 100.0;
        String sql = "INSERT INTO sales (rent_id,price) VALUES (?,?)";
        if(!rentRepository.findById(rentId).isPresent()){
            throw new ResourceNotFoundException("This rent doesn't exists");
        }else{
            Rent rent = rentRepository.findById(rentId).get();
            Double price = ((rent.getEndRental().getTime()-rent.getStartRental().getTime())/86400000)*oneDayPrice;
            jdbcTemplate.update(sql, rentId, price);
        }
    }
}
