package com.staxrt.tutorial.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Data
@Document("summary")
public class Summary {

    @Id
    private String id;

    private List<Sale> saleList;

    private String summaryDate;
}
