package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.model.Summary;
import com.staxrt.tutorial.services.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("summary")
public class SummaryController {
    private final SummaryService summaryService;

    @GetMapping()
    public List<Summary> getAllSummaries() {
        return summaryService.getAllSummaries();
    }

    @PostMapping()
    public Summary setNewSummary() {
        return summaryService.createDaySummary();
    }
}
