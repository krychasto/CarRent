package com.staxrt.tutorial.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "rents")
@EntityListeners(AuditingEntityListener.class)
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_rental", nullable = false)
    private Date startRental;

    @Column(name = "end_rental", nullable = false)
    private Date endRental;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "car_id", nullable = false)
    private Long carId;

}
