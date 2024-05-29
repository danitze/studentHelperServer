package com.danilandreev.diploma.studenthelper.controller;

import com.danilandreev.diploma.studenthelper.model.AddHomeTaskDto;
import com.danilandreev.diploma.studenthelper.model.AddLinkDto;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getClass(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(service.getClass(id));
    }

    @PutMapping("/{id}/hometask")
    public ResponseEntity<?> addHomeTask(
            @PathVariable("id") Long id,
            @RequestBody AddHomeTaskDto dto
    ) {
        return ResponseEntity.ok(service.addHomeTask(id, dto));
    }

    @DeleteMapping("/{id}/hometask")
    public ResponseEntity<?> deleteHomeTask(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(service.deleteHomeTask(id));
    }

    @PutMapping("/{id}/link")
    public ResponseEntity<?> addLink(
            @PathVariable("id") Long id,
            @RequestBody AddLinkDto dto
    ) {
        return ResponseEntity.ok(service.addLink(id, dto));
    }

    @PutMapping("/series/{seriesId}/link")
    public ResponseEntity<?> addLinkToSeries(
            @PathVariable("seriesId") String seriesId,
            @RequestBody AddLinkDto dto
    ) {
        return ResponseEntity.ok(service.addLinkToSeries(seriesId, dto));
    }

    @DeleteMapping("/{id}/link")
    public ResponseEntity<?> deleteLink(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(service.deleteLink(id));
    }

    @DeleteMapping("/series/{seriesId}/link")
    public ResponseEntity<?> deleteLinkFromSeries(
            @PathVariable("seriesId") String seriesId
    ) {
        return ResponseEntity.ok(service.deleteLinkFromSeries(seriesId));
    }

}
