package com.danilandreev.diploma.studenthelper.controller;

import com.danilandreev.diploma.studenthelper.model.CreateClassDto;
import com.danilandreev.diploma.studenthelper.model.CreateClassSeriesDto;
import com.danilandreev.diploma.studenthelper.service.UniversityClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;

@RestController
@RequestMapping("/class")
@RequiredArgsConstructor
public class UniversityClassController {

    private final UniversityClassService service;

    @PostMapping("/series")
    public ResponseEntity<?> createClassSeries(
            @RequestBody CreateClassSeriesDto dto
    ) {
        return ResponseEntity.ok(service.createClassSeries(dto));
    }

    @PostMapping
    public ResponseEntity<?> createClass(
            @RequestBody CreateClassDto dto
    ) {
        return ResponseEntity.ok(service.createClass(dto));
    }

    @GetMapping
    public ResponseEntity<?> getAllClasses(
            @RequestParam(name = "from_date")
            String fromDate,
            @RequestParam(name = "to_date")
            String toDate
    ) {
        Date dateFromDate = Date.from(Instant.parse(fromDate));
        Date dateToDate = Date.from(Instant.parse(toDate));
        return ResponseEntity.ok(service.getAllClasses(dateFromDate, dateToDate));
    }

}
