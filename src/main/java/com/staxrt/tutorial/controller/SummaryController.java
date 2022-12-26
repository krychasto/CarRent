package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.model.Summary;
import com.staxrt.tutorial.services.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableScheduling
@RestController
@RequiredArgsConstructor
@RequestMapping("summary")
public class SummaryController {
    private final SummaryService summaryService;

    @GetMapping()
    public List<Summary> getAllSummaries() {
        return summaryService.getAllSummaries();
    }

    //Fix me
    @Scheduled(fixedDelay = 86400000)
    public void createNewSummary() {
        summaryService.createDaySummary();
    }
}
