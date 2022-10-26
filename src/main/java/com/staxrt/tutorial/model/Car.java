package com.staxrt.tutorial.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;

@Data
@Entity
@Table(name = "cars")
@EntityListeners(AuditingEntityListener.class)
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_brand", nullable = false)
    private String carBrand;

    @Column(name = "vin", nullable = false)
    private String vin;

    @Column(name = "engine_capacity", nullable = false)
    private Float engineCapacity;

    @Override
    public String toString(){
        return "Car{" +
                "id=" + id +
                ", carBrand='" + carBrand + '\'' +
                ", vin='" + vin + '\'' +
                ", engineCapacity='" + engineCapacity + '\'' +
                '}';
    }
}
