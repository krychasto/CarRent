package com.staxrt.tutorial.services;

import com.staxrt.tutorial.model.Sale;
import com.staxrt.tutorial.model.Summary;
import com.staxrt.tutorial.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SummaryService {

    private final MongoTemplate mongoTemplate;
    private final SaleRepository saleRepository;

    public List<Summary> getAllSummaries() {
        return mongoTemplate.findAll(Summary.class);
    }

    public Summary createDaySummary() {
        Summary summary = new Summary();
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime todayEnd = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(99);
        List<Sale> saleList = saleRepository.findAllByInvoiceDateBetween(Date.from(todayStart.toInstant(ZoneOffset.ofHours(0))), Date.from(todayEnd.toInstant(ZoneOffset.ofHours(0))));
        summary.setSaleList(saleList);
        summary.setSummaryDate(LocalDateTime.now().toString());
        mongoTemplate.insert(summary);
        return summary;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        Summary summary = new Summary();
        summary.setSummaryDate("11.11.2000");
        mongoTemplate.insert(summary);
    }

}
