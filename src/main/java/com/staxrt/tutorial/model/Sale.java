package com.staxrt.tutorial.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="sales")
@EntityListeners(AuditingEntityListener.class)
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rent_id",nullable = false)
    private Long rentId;

    @Column(name = "price",nullable = false)
    private Double price;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "invoice_date")
    private Date invoiceDate;

    @Override
    public String toString(){
        return "Sale{" +
                "id=" + id +
                ", rent_id='" + rentId + '\'' +
                ", price='" + price + '\'' +
                ", invoce_date='" + invoiceDate + '\'' +
                '}';
    }
}
